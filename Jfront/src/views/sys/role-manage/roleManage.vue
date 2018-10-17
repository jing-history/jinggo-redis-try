<style lang="less">
  @import "./roleManage.less";
</style>
<template>
  <div class="search">
    <Row>
      <Col>
      <Card>
        <Row class="operation">
          <Button @click="addRole" type="primary" icon="md-add">添加角色</Button>
          <Button @click="delAll" icon="md-trash">批量删除</Button>
          <Button @click="init" icon="md-refresh">刷新</Button>
        </Row>
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
        getRoleList
    } from "@/api/index";
    export default {
        name: "role-manage",
        data() {
            return {
                loading: true,
                treeLoading: true,
                submitPermLoading: false,
                sortColumn: "createTime",
                sortType: "desc",
                modalType: 0,
                roleModalVisible: false,
                permModalVisible: false,
                modalTitle: "",
                roleForm: {
                    description: ""
                },
                roleFormValidate: {
                    name: [{ required: true, message: "角色名称不能为空", trigger: "blur" }]
                },
                submitLoading: false,
                selectList: [],
                selectCount: 0
            }
        },
        methods: {
            init() {
                this.getRoleList();
                // 获取所有菜单权限树
                this.getPermList();
            },
            getRoleList() {
                this.loading = true;
                let params = {
                    pageNumber: this.pageNumber,
                    pageSize: this.pageSize,
                    sort: this.sortColumn,
                    order: this.sort
                };
                getRoleList(params).then(res => {
                    this.loading = false;
                    if (res.success === true) {
                        this.data = res.result.content;
                        this.total = res.result.totalElements;
                    }
                });
            }
        },
        mounted() {
            this.init();
        }
    }
</script>