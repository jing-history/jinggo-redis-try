<style lang="less">
    @import "./departmentManage.less";
</style>
<template>
    <div class="search">
        <Card>
            <Row class="operation">
                <Button @click="add" type="primary" icon="md-add">添加子部门</Button>
                <Button @click="addRoot" icon="md-add">添加一级部门</Button>
                <Button @click="delAll" icon="md-trash">批量删除</Button>
                <Button @click="getParentList" icon="md-refresh">刷新</Button>
            </Row>
            <Row type="flex" justify="start" class="code-row-bg">
                <Col span="6">
                <Alert show-icon>
                    当前选择编辑： <span class="select-count">{{editTitle}}</span>
                    <a class="select-clear" v-if="form.id" @click="canelEdit">取消选择</a>
                </Alert>
                <Tree :data="data" :load-data="loadData" show-checkbox @on-check-change="changeSelect" @on-select-change="selectTree"></Tree>
                <Spin size="large" fix v-if="loading"></Spin>
                </Col>
            </Row>
        </Card>
        <Modal :title="modalTitle" v-model="menuModalVisible" :mask-closable='false' :width="500">
            <Form ref="formAdd" :model="formAdd" :label-width="85" :rules="menuFormValidate">
                <div v-if="showParent">
                    <FormItem label="上级部门：">
                        {{form.title}}
                    </FormItem>
                </div>
                <FormItem label="部门名称" prop="title">
                    <Input v-model="formAdd.title"/>
                </FormItem>
                <FormItem label="排序值" prop="sortOrder">
                    <InputNumber :max="1000" :min="0" v-model="formAdd.sortOrder"></InputNumber>
                    <span style="margin-left:5px">值越小越靠前，支持小数</span>
                </FormItem>
                <FormItem label="是否启用" prop="status">
                    <i-switch size="large" v-model="addStatus" @on-change="changeAddSwitch">
                        <span slot="open">启用</span>
                        <span slot="close">禁用</span>
                    </i-switch>
                </FormItem>
            </Form>
            <div slot="footer">
                <Button type="text" @click="cancelAdd">取消</Button>
                <Button type="primary" :loading="submitLoading" @click="submitAdd">提交</Button>
            </div>
        </Modal>
    </div>
</template>
<script>
    import {
        initDepartment,
        loadDepartment,
        addDepartment
    } from "@/api/index";
    export default {
        name: "department-manage",
        data() {
            return {
                loading: true,
                loadingEdit: true,
                menuModalVisible: false,
                selectList: [],
                selectCount: 0,
                showParent: false,
                editStatus: true,
                addStatus: true,
                modalTitle: "",
                editTitle: "",
                form: {
                    id: "",
                    parentId: "",
                    parentTitle: "",
                    sortOrder: null,
                    status: 0,
                    url: ""
                },
                formAdd: {},
                menuFormValidate: {
                    title: [{ required: true, message: "名称不能为空", trigger: "blur" }]
                },
                submitLoading: false,
                data: [],
                dataEdit: []
            };
        },
        methods: {
            init() {
                this.getParentList();
                this.getParentListEdit();
            },
            getParentList() {
                this.loading = true;
                initDepartment().then(res => {
                    this.loading = false;
                    if (res.success === true) {
                        res.result.forEach(function(e) {
                            if (e.isParent) {
                                e.loading = false;
                                e.children = [];
                            }
                        });
                        this.data = res.result;
                    }
                });
            },
            getParentListEdit() {
                this.loadingEdit = true;
                initDepartment().then(res => {
                    this.loadingEdit = false;
                    if (res.success === true) {
                        res.result.forEach(function(e) {
                            if (e.isParent) {
                                e.loading = false;
                                e.children = [];
                            }
                        });
                        // 头部加入一级
                        let first = {
                            id: "0",
                            title: "一级部门"
                        };
                        res.result.unshift(first);
                        this.dataEdit = res.result;
                    }
                });
            },
            add() {

            },
            addRoot() {
                this.modalTitle = "添加一级部门";
                this.showParent = false;
                this.formAdd = {
                    parentId: 0,
                    sortOrder: 1,
                    status: 0
                };
                this.menuModalVisible = true;
            },
            delAll() {

            },
            canelEdit() {

            },
            loadData(item, callback) {
                loadDepartment(item.id).then(res => {
                    if (res.success === true) {
                        res.result.forEach(function(e) {
                            if (e.isParent) {
                                e.loading = false;
                                e.children = [];
                            }
                        });
                        callback(res.result);
                    }
                });
            },
            changeSelect() {

            },
            changeAddSwitch(v) {
                if (v) {
                    this.formAdd.status = 0;
                } else {
                    this.formAdd.status = -1;
                }
            },
            cancelAdd() {
                this.menuModalVisible = false;
            },
            submitAdd() {
                this.$refs.formAdd.validate(valid => {
                    if (valid) {
                        this.submitLoading = true;
                        if (this.formAdd.sortOrder === null) {
                            this.formAdd.sortOrder = "";
                        }
                        if (this.formAdd.buttonType === null) {
                            this.formAdd.buttonType = "";
                        }
                        addDepartment(this.formAdd).then(res => {
                            this.submitLoading = false;
                            if (res.success === true) {
                                this.$Message.success("添加成功");
                                this.init();
                                this.menuModalVisible = false;
                            }
                        });
                    }
                });
            },
            selectTree(v) {
                if (v.length > 0) {
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
                    let data = JSON.parse(str);
                    this.form = data;
                    this.editTitle = data.title;
                }
            }
        },
        mounted() {
            this.init();
        }
    }
</script>