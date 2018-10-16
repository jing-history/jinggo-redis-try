<style lang="less">
  @import "./menuManage.less";
</style>
<template>
  <div class="search">
    <Card>
      <Row class="operation">
        <Button @click="addMenu" type="primary" icon="md-add">添加子节点</Button>
        <Button @click="addRootMenu" icon="md-add">添加一级菜单</Button>
        <Button @click="delAll" icon="md-trash">批量删除</Button>
        <Dropdown @on-click="handleDropdown">
          <Button>
            更多操作
            <Icon type="md-arrow-dropdown"></Icon>
          </Button>
          <DropdownMenu slot="list">
            <DropdownItem name="refresh">刷新</DropdownItem>
            <DropdownItem name="expandOne">仅展开一级</DropdownItem>
            <DropdownItem name="expandTwo">仅展开两级</DropdownItem>
            <DropdownItem name="expandAll">展开所有</DropdownItem>
          </DropdownMenu>
        </Dropdown>
      </Row>
      <Row type="flex" justify="start" class="code-row-bg">
        <Col span="6">
        <Alert show-icon>
          当前选择编辑： <span class="select-count">{{editTitle}}</span>
          <a class="select-clear" v-if="menuForm.id" @click="canelEdit">取消选择</a>
        </Alert>
        <Tree :data="data" show-checkbox @on-check-change="changeSelect" @on-select-change="selectTree"></Tree>
        <Spin size="large" fix v-if="loading"></Spin>
        </Col>
      </Row>
    </Card>
  </div>
</template>
<script>
    import {
        getAllPermissionList
    } from "@/api/index";
    export default {
        name: "menu-manage",
        data() {
          return {
              loading: true,
              expandLevel: 1,
              menuModalVisible: false,
              selectList: [],
              selectCount: 0,
              showParent: false,
              parentTitle: "",
              isButtonAdd: false,
              isMenuAdd: false,
              isMenu: false,
              isButton: false,
              editStatus: true,
              addStatus: true,
              editTitle: "",
              modalTitle: "",
              data: [],
              menuForm: {
                  id: "",
                  parentId: "",
                  buttonType: "",
                  type: 0,
                  sortOrder: null,
                  level: null,
                  status: 0,
                  url: ""
              }
          };
        },
        methods: {
            init() {
                this.getAllList();
            },
            getAllList() {
                this.loading = true;
                this.getRequest("/permission/getAllList").then(res => {
                    this.loading = false;
                    if (res.success === true) {
                        // 仅展开指定级数 默认所有展开
                        let expandLevel = this.expandLevel;
                        res.result.forEach(function(e) {
                            if (expandLevel === 1) {
                                if (e.level === 1) {
                                    e.expand = false;
                                }
                                if (e.children && e.children.length > 0) {
                                    e.children.forEach(function(c) {
                                        if (c.level === 2) {
                                            c.expand = false;
                                        }
                                    });
                                }
                            } else {
                                if (e.children && e.children.length > 0) {
                                    e.children.forEach(function(c) {
                                        if (expandLevel === 2) {
                                            if (c.level === 2) {
                                                c.expand = false;
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    }
                    this.data = res.result;
                });
            },
            addMenu() {

            },
            addRootMenu() {

            },
            delAll() {

            },
            handleDropdown() {

            },
            canelEdit() {
                this.isMenu = false;
                this.isButton = false;
                this.$refs.menuForm.resetFields();
                delete this.menuForm.id;
                this.editTitle = "";
            },
            changeSelect(v) {
                this.selectCount = v.length;
                this.selectList = v;
            },
            selectTree(v) {
                if (v.length > 0) {
                    if (Number(v[0].level) === 1 || Number(v[0].level) === 2) {
                        this.isButton = false;
                        this.isMenu = true;
                    } else {
                        this.isButton = true;
                        this.isMenu = false;
                    }
                    if (Number(v[0].status) === 0) {
                        this.editStatus = true;
                    } else {
                        this.editStatus = false;
                    }
                    // 转换null为""
                    for (let attr in v[0]) {
                        if (v[0][attr] === null) {
                            v[0][attr] = "";
                        }
                    }
                    let str = JSON.stringify(v[0]);
                    let menu = JSON.parse(str);
                    this.menuForm = menu;
                    this.editTitle = menu.title;
                }
            }
        },
        mounted() {
            this.init();
        }
    }
</script>