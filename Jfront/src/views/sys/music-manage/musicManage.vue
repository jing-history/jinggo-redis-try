<style lang="less">
    @import "./musicManage.less";
</style>
<template>
    <div class="search">
        <Row>
            <Col>
            <Card>
                <Form inline :label-width="70" class="search-form">
                    <Form-item label="搜索歌曲">
                        <Input type="text" v-model="searchKey" clearable placeholder="请输入搜索歌曲关键词" style="width: 200px"/>
                    </Form-item>
                    <Form-item label="创建时间">
                        <DatePicker type="date" v-model="selectDate" format="yyyy-MM-dd" clearable @on-change="selectDateRange" placeholder="选择起始时间" style="width: 200px"></DatePicker>
                    </Form-item>
                    <Form-item style="margin-left:-35px;" class="br">
                        <Button @click="getMusicList"  type="primary" icon="ios-search">搜索</Button>
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
            <Form ref="musicForm" :model="musicForm" :label-width="70" :rules="formValidate">
                <FormItem label="歌名" prop="name">
                    <Input v-model="musicForm.name" autocomplete="off"/>
                </FormItem>
                <FormItem label="代码" prop="code">
                    <Input v-model="musicForm.code"/>
                </FormItem>
                <FormItem label="歌词" prop="content">
                    <Input type="textarea" v-model="musicForm.content"/>
                </FormItem>
            </Form>
            <div slot="footer">
                <Button type="text" @click="cancelMusic">取消</Button>
                <Button type="primary" :loading="submitLoading" @click="submitMusic">提交</Button>
            </div>
        </Modal>
    </div>
</template>
<script>
    import {
        getMusicListData,
        addMusic,
        editMusic,
        disableMusic
    } from "@/api/index";
    export default {
        name: "music-manage",
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
                modalTitle: "",
                modalVisible: false,  //弹出框是否可见
                accessToken: {},
                imgUrl:"",    //大图的url
                musicForm: {
                    name: "",
                    code: "",
                    content: "",
                    status: ""
                },
                formValidate: {
                    name: [
                        { required: true, message: "歌曲不能为空", trigger: "blur" }
                    ],
                    content: [
                        { required: true, message: "歌词不能为空", trigger: "blur" }
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
                        title: "歌名",
                        key: "name",
                        width: 150,
                        tooltip: true
                    },
                    {
                        title: "代码",
                        key: "code",
                        tooltip: true
                    },
                    {
                        title: "歌词",
                        key: "content",
                        tooltip: true
                    },
                    {
                        title: "状态",
                        key: "status",
                        align: "center",
                        width: 140,
                        render: (h, params) => {
                            let re = "";
                            if (params.row.status === 0) {
                                return h("div", [
                                    h(
                                        "Tag",
                                        {
                                            props: {
                                                type: "dot",
                                                color: "success"
                                            }
                                        },
                                        "正常启用"
                                    )
                                ]);
                            } else if (params.row.status === 1) {
                                return h("div", [
                                    h(
                                        "Tag",
                                        {
                                            props: {
                                                type: "dot",
                                                color: "error"
                                            }
                                        },
                                        "禁用"
                                    )
                                ]);
                            }
                        },
                        filters: [
                            {
                                label: "正常启用",
                                value: 0
                            },
                            {
                                label: "禁用",
                                value: 1
                            }
                        ],
                        filterMultiple: false,
                        filterMethod(value, row) {
                            if (value === 0) {
                                return row.status === 0;
                            } else if (value === -1) {
                                return row.status === -1;
                            }
                        }
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
                this.getMusicList();
            },
            changeSort(e) {

            },
            changeSelect(e) {
            },
            changePage(v) {
                this.pageNumber = v;
                this.getMusicList();
                this.clearSelectAll();
            },
            changePageSize(v) {
                this.pageSize = v;
                this.getMusicList();
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
                this.getMusicList();
            },  // end 上面常用的方法

            clearSelectAll() {
                this.$refs.table.selectAll(false);
            },
            getMusicList() {
                this.loading = true;
                let params = {
                    title: this.searchKey,
                    pageNumber: this.pageNumber,
                    pageSize: this.pageSize,
                    sort: this.sortColumn,
                    order: this.sortType,
                    startDate: this.selectDate,
                };
                getMusicListData(params).then(res => {
                    this.loading = false;
                    if (res.success === true) {
                        this.data = res.result.content;
                        this.total = res.result.totalElements;
                    }
                });
            },
            add() {
                this.modalType = 0;
                this.modalTitle = "添加歌曲";
                this.$refs.musicForm.resetFields();
                this.modalVisible = true;
            },
            edit(v) {
                this.modalType = 1;
                this.modalTitle = "修改歌曲";
                // 转换null为""
                for (let attr in v) {
                    if (v[attr] === null) {
                        v[attr] = "";
                    }
                }
                let str = JSON.stringify(v);
                let timeInfo = JSON.parse(str);
                this.musicForm = timeInfo;
                this.modalVisible = true;
            },
            disable(v) {
                this.$Modal.confirm({
                    title: "确认禁用",
                    content: "您确认要禁用歌曲 " + v.name + " ?",
                    onOk: () => {
                        this.operationLoading = true;
                        disableMusic(v.id).then(res => {
                            this.operationLoading = false;
                            if (res.success === true) {
                                this.$Message.success("操作成功");
                                this.getMusicList();
                            }
                        });
                    }
                });
            },
            remove(v) {
                this.$Message.warning("数据来之不易，请忽乱删😱");
                return;
            },
            delAll() {
                this.$Message.warning("不可以乱删除数据哦😄");
                return;
            },
            cancelMusic() {
                this.modalVisible = false;
            },
            submitMusic() {
                this.$refs.musicForm.validate(valid => {
                    if (valid) {
                        if (this.modalType === 0) {
                            // 添加
                            this.submitLoading = true;
                            addMusic(this.musicForm).then(res => {
                                this.submitLoading = false;
                                if (res.success === true) {
                                    this.$Message.success("操作成功");
                                    this.getMusicList();
                                    this.modalVisible = false;
                                }
                            });
                        } else {
                            //修改
                            this.submitLoading = true;
                            editMusic(this.musicForm).then(res => {
                                this.submitLoading = false;
                                if (res.success === true) {
                                    this.$Message.success("修改成功");
                                    this.getMusicList();
                                    this.modalVisible = false;
                                }
                            });
                        }
                    }
                });
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