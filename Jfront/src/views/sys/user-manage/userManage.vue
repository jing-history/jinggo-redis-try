<style lang="less">
    @import "./userManage.less";
</style>

<template>
    <div class="search">
        <Row>
            <Col>
                <Card>
                    <Row>
                        <Form ref="searchForm" :model="searchForm" inline :label-width="70" class="search-form">
                            <Form-item label="用户名称" prop="username">
                                <Input type="text" v-model="searchForm.username" clearable placeholder="请输入用户名" style="width: 200px"/>
                            </Form-item>
                            <Form-item label="部门" prop="department">
                                <Cascader v-model="selectDep" :data="department" :load-data="loadData" @on-change="handleChangeDep" change-on-select filterable placeholder="请选择或输入搜索部门" style="width: 200px"></Cascader>
                            </Form-item>
                            <span v-if="drop">
                            <Form-item label="手机号" prop="mobile">
                              <Input type="text" v-model="searchForm.mobile" clearable placeholder="请输入手机号" style="width: 200px"/>
                            </Form-item>
                              <Form-item label="邮箱" prop="email">
                                <Input type="text" v-model="searchForm.email" clearable placeholder="请输入邮箱" style="width: 200px"/>
                              </Form-item>
                              <Form-item label="性别" prop="sex">
                                <Select v-model="searchForm.sex" placeholder="请选择" clearable style="width: 200px">
                                  <Option value="0">女</Option>
                                  <Option value="1">男</Option>
                                </Select>
                              </Form-item>
                              <Form-item label="用户类型" prop="type">
                                <Select v-model="searchForm.type" placeholder="请选择" clearable style="width: 200px">
                                  <Option value="0">普通用户</Option>
                                  <Option value="1">管理员</Option>
                                </Select>
                              </Form-item>
                              <Form-item label="用户状态" prop="status">
                                <Select v-model="searchForm.status" placeholder="请选择" clearable style="width: 200px">
                                  <Option value="0">正常</Option>
                                  <Option value="-1">禁用</Option>
                                </Select>
                              </Form-item>
                              <Form-item label="创建时间">
                                <DatePicker v-model="selectDate" type="daterange" format="yyyy-MM-dd" clearable @on-change="selectDateRange" placeholder="选择起始时间" style="width: 200px"></DatePicker>
                              </Form-item>
                            </span>
                            <Form-item style="margin-left:-35px;" class="br">
                                <Button @click="handleSearch" type="primary" icon="ios-search">搜索</Button>
                                <Button @click="handleReset" >重置</Button>
                                <a class="drop-down" @click="dropDown">{{dropDownContent}}
                                    <Icon :type="dropDownIcon"></Icon>
                                </a>
                            </Form-item>
                        </Form>
                    </Row>
                </Card>
            </Col>
        </Row>
    </div>
</template>

<script>
    import {
        initDepartment,
        loadDepartment
    } from "@/api/index";

    export default {
        name: "user-manage",
        data() {
            const validatePassword = (rule, value, callback) => {
                if (value.length < 6) {
                    callback(new Error("密码长度不得小于6位"));
                } else {
                    callback();
                }
            };
            const validateMobile = (rule, value, callback) => {
                var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
                if (!reg.test(value)) {
                    callback(new Error("手机号格式错误"));
                } else {
                    callback();
                }
            };

            return {
                department: [],
                selectDep: [],
                searchForm: {
                    username: "",
                    departmentId: "",
                    mobile: "",
                    email: "",
                    sex: "",
                    type: "",
                    status: "",
                    pageNumber: 1,
                    pageSize: 10,
                    sort: "createTime",
                    order: "desc",
                    startDate: "",
                    endDate: ""
                },
            }
        }
    }
</script>