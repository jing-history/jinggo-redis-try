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
