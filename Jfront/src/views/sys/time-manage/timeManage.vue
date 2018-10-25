<style lang="less">
  @import "./timeManage.less";
</style>
<template>
    <div class="search">
        <Row>
            <Col>
            <Card>
                <Form inline :label-width="70" class="search-form">
                    <Form-item label="搜索标题">
                        <Input type="text" v-model="searchKey" clearable placeholder="请输入搜索标题关键词" style="width: 200px"/>
                    </Form-item>
                    <Form-item label="创建时间">
                        <DatePicker type="date" v-model="selectDate" format="yyyy-MM-dd" clearable @on-change="selectDateRange" placeholder="选择起始时间" style="width: 200px"></DatePicker>
                    </Form-item>
                    <Form-item style="margin-left:-35px;" class="br">
                        <Button @click="getTimeList"  type="primary" icon="ios-search">搜索</Button>
                        <Button @click="handleReset" >重置</Button>
                    </Form-item>
                </Form>
                <Row class="operation">
                    <Button @click="add" type="primary" icon="md-add">添加</Button>
                    <Button @click="delAll" icon="md-trash">批量删除</Button>
                </Row>
                <Row>
                    <Alert show-icon>
                        已选择 <span class="select-count">{{selectCount}}</span> 项
                        <a class="select-clear" @click="clearSelectAll">清空</a>
                    </Alert>
                </Row>
                <Row>
                    <Table :loading="loading" border :columns="columns" :data="data" ref="table" sortable="custom" ></Table>
                </Row>
                <Row type="flex" justify="end" class="page">
                    <Page :current="pageNumber" :total="total" :page-size="pageSize" @on-change="changePage" @on-page-size-change="changePageSize" :page-size-opts="[10,20,50]" size="small" show-total show-elevator show-sizer></Page>
                </Row>
            </Card>
            </Col>
        </Row>
        <Modal :title="modalTitle" v-model="modalVisible" :mask-closable='false' :width="500" :styles="{top: '30px'}">
            <Form ref="timeForm" :model="timeForm" :label-width="70" :rules="formValidate">
                <FormItem label="标题" prop="title">
                    <Input v-model="timeForm.title" autocomplete="off"/>
                </FormItem>
                <FormItem label="内容" prop="content">
                    <Input v-model="timeForm.content"/>
                </FormItem>
                <Form-item label="图片" prop="figureImg">
                    <Poptip trigger="hover" title="图片预览" placement="right" width="350">
                        <Input v-model="timeForm.figureImg" placeholder="可直接填入网络图片链接" clearable/>
                        <div slot="content">
                            <img :src="timeForm.figureImg" alt="无效的图片链接" style="width: 100%;margin: 0 auto;display: block;">
                            <a @click="viewPic()" style="margin-top:5px;text-align:right;display:block">查看原图</a>
                        </div>
                    </Poptip>
                    <Upload action="/xboot/upload/file"
                            :headers="accessToken"
                            :on-success="handleSuccess"
                            :on-error="handleError"
                            :format="['jpg','jpeg','png','gif']"
                            :max-size="5120"
                            :on-format-error="handleFormatError"
                            :on-exceeded-size="handleMaxSize"
                            :before-upload="beforeUpload"
                            ref="up"
                            class="upload">
                        <Button icon="ios-cloud-upload-outline">上传图片</Button>
                    </Upload>
                </Form-item>
                <FormItem label="图片说明" prop="figureMsg">
                    <Input v-model="timeForm.figureMsg"/>
                </FormItem>
                <FormItem label="标签说明" prop="figcaption">
                    <Input v-model="timeForm.figcaption"/>
                </FormItem>
            </Form>
            <div slot="footer">
                <Button type="text" @click="cancelTime">取消</Button>
                <Button type="primary" :loading="submitLoading" @click="submitTime">提交</Button>
            </div>
        </Modal>
        <Modal title="查看大图" v-model="viewImage" :styles="{top: '30px'}">
            <img :src="imgUrl" alt="图片预览" style="width: 100%;margin: 0 auto;display: block;">
        </Modal>
    </div>
</template>
<script>
    import {
        getTimeListData,
        addTime,
        editTime
    } from "@/api/index";
    export default {
        name: "time-manage",
        data() {
          return {
              loading: true,
              submitLoading: false,
              selectCount: 0,
              searchKey: "",
              selectDate: null,
              sortColumn: "createTime",
              sortType: "desc",
              modalType: 0, //新增还是修改 0 新增 1 修改
              viewImage: false,          //图片是否可见
              modalTitle: "",
              modalVisible: false,  //弹出框是否可见
              accessToken: {},
              imgUrl:"",    //大图的url
              timeForm: {
                  title: "",
                  content: "",
                  figureImg: "https://s1.ax1x.com/2018/05/19/CcdVQP.png",
                  figureMsg: "",
                  figcaption: ""
              },
              formValidate: {
                  title: [
                      { required: true, message: "标题不能为空", trigger: "blur" }
                  ],
                  figureImg: [
                      { required: true, message: "图片不能为空", trigger: "blur" }
                  ]
              },
              columns: [
                  {
                      type: "selection",
                      width: 50,
                      align: "center"
                  },
                  {
                      type: "index",
                      width: 40,
                      align: "center"
                  },
                  {
                      title: "标题",
                      key: "title",
                      width: 150,
                      tooltip: true
                  },
                  {
                      title: "内容",
                      key: "content",
                      tooltip: true
                  },
                  {
                      title: "图片",
                      key: "figureImg",
                      width: 200,
                      align: "center",
                      render: (h, params) => {
                          return h("img", {
                              props: {
                                  src: params.row.figureImg
                              },
                              style:{
                                  'margin-top':'10px',
                                  'margin-bottom':'10px',
                                  'border-radius':'4px',
                                  width:'150px',
                                  height:'100px',
                                  cursor: 'pointer',
                              },
                              attrs:{
                                  'src': params.row.figureImg,
                                  onerror:'this.src="https://goss.veer.com/creative/vcg/veer/800water/veer-133632476.jpg"'
                              },
                              on:{
                                  click:(e)=>{
                                      this.handleView(params.row.figureImg);
                                  }
                              }
                          });
                      }
                  },
                  {
                      title: "图片说明",
                      key: "figureMsg",
                      width: 120,
                  },
                  {
                      title: "标签说明",
                      key: "figcaption",
                      tooltip: true
                  },
                  {
                      title: "创建时间",
                      key: "createTime",
                      width: 100,
                      render: (h,params)=>{
                          return h('div',
                              this.formatDate(new Date(params.row.createTime),'yyyy-MM-dd')
                          )
                      }
                  },
                  {
                      title: "操作",
                      key: "action",
                      align: "center",
                      width: 180,
                      render: (h, params) => {
                          return h("div", [
                              h(
                                  "Button",
                                  {
                                      props: {
                                          type: "primary",
                                          size: "small"
                                      },
                                      style: {
                                          marginRight: "5px"
                                      },
                                      on: {
                                          click: () => {
                                              this.edit(params.row);
                                          }
                                      }
                                  },
                                  "编辑"
                              ),
                              h(
                                  "Button",
                                  {
                                      props: {
                                          size: "small"
                                      },
                                      style: {
                                          marginRight: "5px"
                                      },
                                      on: {
                                          click: () => {
                                              this.disable(params.row);
                                          }
                                      }
                                  },
                                  "禁用"
                              ),
                              h(
                                  "Button",
                                  {
                                      props: {
                                          type: "error",
                                          size: "small"
                                      },
                                      on: {
                                          click: () => {
                                              this.remove(params.row);
                                          }
                                      }
                                  },
                                  "删除"
                              )
                          ]);
                      }
                  }
              ],
              data: [],
              pageNumber: 1,
              pageSize: 10,
              total: 0,
              startDate: "",
              endDate: ""
          };
        },
        methods: {
            //前面固定分页需要
            init() {
                this.accessToken = {
                    accessToken: this.getStore("accessToken")
                };
                this.getTimeList();
            },
            changeSort(e) {

            },
            changeSelect(e) {
            },
            changePage(v) {
                this.pageNumber = v;
                this.getQuartzList();
                this.clearSelectAll();
            },
            changePageSize(v) {
                this.pageSize = v;
                this.getQuartzList();
            },
            selectDateRange(v) {
                if (v) {
                    this.startDate = v[0];
                    this.endDate = v[1];
                }
            },
            handleReset() {
                this.searchKey = "";
                this.selectDate = null;
                this.startDate = "";
                this.endDate = "";
                this.getTimeList();
            },  // end 上面常用的方法

            clearSelectAll() {
                this.$refs.table.selectAll(false);
            },
            getTimeList() {
                this.loading = true;
                let params = {
                    title: this.searchKey,
                    pageNumber: this.pageNumber,
                    pageSize: this.pageSize,
                    sort: this.sortColumn,
                    order: this.sortType,
                    startDate: this.selectDate,
                };
                getTimeListData(params).then(res => {
                    this.loading = false;
                    if (res.success === true) {
                        this.data = res.result.content;
                        this.total = res.result.totalElements;
                    }
                });
            },
            add() {
                this.modalType = 0;
                this.modalTitle = "添加时间轴";
                this.$refs.timeForm.resetFields();
                this.modalVisible = true;
            },
            edit(v) {
                this.modalType = 1;
                this.modalTitle = "修改时间轴";
                // 转换null为""
                for (let attr in v) {
                    if (v[attr] === null) {
                        v[attr] = "";
                    }
                }
                let str = JSON.stringify(v);
                let timeInfo = JSON.parse(str);
                this.timeForm = timeInfo;
                this.modalVisible = true;
            },
            disable(v) {
                this.$Message.warning("暂时不用禁用啊😱");
                return;
            },
            remove(v) {
                this.$Message.warning("数据来之不易，请忽乱删😱");
                return;
            },
            delAll() {
                this.$Message.warning("不可以乱删除数据哦😄");
                return;
            },
            cancelTime() {
                this.modalVisible = false;
            },
            submitTime() {
                this.$refs.timeForm.validate(valid => {
                    if (valid) {
                        if (this.modalType === 0) {
                            // 添加
                            this.submitLoading = true;
                            addTime(this.timeForm).then(res => {
                                this.submitLoading = false;
                                if (res.success === true) {
                                    this.$Message.success("操作成功");
                                    this.getTimeList();
                                    this.modalVisible = false;
                                }
                            });
                        } else {
                            //修改
                            this.submitLoading = true;
                            editTime(this.timeForm).then(res => {
                                this.submitLoading = false;
                                if (res.success === true) {
                                    this.$Message.success("修改成功");
                                    this.getTimeList();
                                    this.modalVisible = false;
                                }
                            });
                        }
                    }
                });
            },
            //图片上传
            viewPic() {
                this.viewImage = true;
            },
            handleView(url) {
                this.imgUrl = url;
                this.viewImage = true;
            },
            handleSuccess(res, file) {
                if (res.success === true) {
                    file.url = "http://" + res.result;
                    this.timeForm.figureImg = "http://" + res.result;
                } else {
                    this.$Message.error(res.message);
                }
            },
            handleError(error, file, fileList) {
                this.$Message.error(error.toString());
            },
            handleFormatError(file) {
                this.$Notice.warning({
                    title: "不支持的文件格式",
                    desc:
                    "所选文件‘ " +
                    file.name +
                    " ’格式不正确, 请选择 .jpg .jpeg .png .gif格式文件"
                });
            },
            handleMaxSize(file) {
                this.$Notice.warning({
                    title: "文件大小过大",
                    desc: "所选文件‘ " + file.name + " ’大小过大, 不得超过 5M."
                });
            },
            beforeUpload() {
                /* todo 按钮权限
                if (!this.$route.meta.permTypes.includes("upload")) {
                    this.$Message.error("此处您没有上传权限(为演示功能，该按钮未配置隐藏)");
                    return false;
                }*/
                return true;
            },
            formatDate(date, fmt) {
                let o = {
                    'M+': date.getMonth() + 1, // 月份
                    'd+': date.getDate(), // 日
                    'h+': date.getHours(), // 小时
                    'm+': date.getMinutes(), // 分
                    's+': date.getSeconds(), // 秒
                    'S': date.getMilliseconds() // 毫秒
                }
                if (/(y+)/.test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
                }
                for (var k in o) {
                    if (new RegExp('(' + k + ')').test(fmt)) {
                        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
                    }
                }
                return fmt;
            }
        },
        mounted() {
            this.init();
        }
    };
</script>