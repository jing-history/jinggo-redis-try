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
                <Row>
                    <Alert show-icon>
                        已选择 <span class="select-count">{{selectCount}}</span> 项
                        <a class="select-clear" @click="clearSelectAll">清空</a>
                    </Alert>
                </Row>
                <Row>
                    <Table :loading="loading" border :columns="columns" :data="data" ref="table" sortable="custom" @on-sort-change="changeSort" @on-selection-change="changeSelect"></Table>
                </Row>
                <Row type="flex" justify="end" class="page">
                    <Page :current="pageNumber" :total="total" :page-size="pageSize" @on-change="changePage" @on-page-size-change="changePageSize" :page-size-opts="[10,20,50]" size="small" show-total show-elevator show-sizer></Page>
                </Row>
            </Card>
            </Col>
        </Row>
    </div>
</template>
<script>
    import {
        getTimeListData
    } from "@/api/index";
    export default {
        name: "time-manage",
        data() {
          return {
              loading: true,
              selectCount: 0,
              searchKey: "",
              selectDate: null,
              sortColumn: "createTime",
              sortType: "desc",
              columns: [
                  {
                      type: "selection",
                      width: 60,
                      align: "center"
                  },
                  {
                      type: "index",
                      width: 60,
                      align: "center"
                  },
                  {
                      title: "任务类名",
                      key: "jobClassName",
                      sortable: true
                  },
                  {
                      title: "操作",
                      key: "action",
                      align: "center",
                      width: 280,
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
                this.getLoveList();
            },  // end 上面常用的方法

            clearSelectAll() {
                this.$refs.table.selectAll(false);
            },
            init() {
                this.getTimeList();
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
            edit(v) {

            },
            remove(v) {

            }
        },
        mounted() {
            this.init();
        }
    };
</script>