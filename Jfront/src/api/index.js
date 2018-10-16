// 统一请求路径前缀在libs/axios.js中修改
import { getRequest, postRequest, putRequest, deleteRequest, uploadFileRequest } from '@/libs/axios';

//登录
export const login = (params) => {
    return postRequest('/login', params)
}
// 获取用户登录信息
export const userInfo = (params) => {
    return getRequest('/user/info', params)
}

// IP天气信息
export const ipInfo = (params) => {
    return getRequest('/common/ip/info', params)
}

// 获取用户数据 多条件
export const getUserListData = (params) => {
    return getRequest('/user/getByCondition', params)
}

// 获取全部角色数据
export const getAllRoleList = (params) => {
    return getRequest('/role/getAllList', params)
}

// 添加用户
export const addUser = (params) => {
    return postRequest('/user/admin/add', params)
}
// 编辑用户
export const editUser = (params) => {
    return postRequest('/user/admin/edit', params)
}
// 启用用户
export const enableUser = (id, params) => {
    return postRequest(`/user/admin/enable/${id}`, params)
}

// 禁用用户
export const disableUser = (id, params) => {
    return postRequest(`/user/admin/disable/${id}`, params)
}

// 删除用户
export const deleteUser = (ids, params) => {
    return deleteRequest(`/user/delByIds/${ids}`, params)
}

// 获取全部用户数据
export const getAllUserData = (params) => {
    return getRequest('/user/getAll', params)
}

// 第二个标签也买呢的链接 获取一级部门
export const initDepartment = (params) => {
    return getRequest('/department/getByParentId/0', params)
}
// 加载部门子级数据
export const loadDepartment = (id, params) => {
    return getRequest(`/department/getByParentId/${id}`, params)
}
// 添加部门
export const addDepartment = (params) => {
    return postRequest('/department/add', params)
}
// 编辑部门
export const editDepartment = (params) => {
    return postRequest('/department/edit', params)
}
// 删除部门
export const deleteDepartment = (ids, params) => {
    return deleteRequest(`/department/delByIds/${ids}`, params)
}

// 获取全部权限数据
export const getAllPermissionList = (params) => {
    return getRequest('/permission/getAllList', params)
}
// 添加权限
export const addPermission = (params) => {
    return postRequest('/permission/add', params)
}
// 编辑权限
export const editPermission = (params) => {
    return postRequest('/permission/edit', params)
}
// 删除权限
export const deletePermission = (ids, params) => {
    return deleteRequest(`/permission/delByIds/${ids}`, params)
}