/**
 * Created by gz12 on 2018-09-26.
 */
import axios from 'axios';
import lazyLoading from './lazyLoading.js';
import router from '@/router/index';
import Cookies from "js-cookie";

let util = {

};

util.title = function (title) {
    title = title || 'X-Boot 前后端分离开发平台';
    window.document.title = title;
};

util.inOf = function (arr, targetArr) {
    let res = true;
    arr.forEach(item => {
        if (targetArr.indexOf(item) < 0) {
            res = false;
        }
    });
    return res;
};

util.oneOf = function (ele, targetArr) {
    if (targetArr.indexOf(ele) >= 0) {
        return true;
    } else {
        return false;
    }
};

util.initRouter = function (vm) {
    const constRoutes = [];
    const otherRoutes = [];

    // 404路由需要和动态路由一起注入
    const otherRouter = [{
        path: '/*',
        name: 'error-404',
        meta: {
            title: '404-页面不存在'
        },
        component: 'error-page/404'
    }];

    // 判断用户是否登录
    let userInfo = Cookies.get('userInfo')
    if (userInfo === null || userInfo === "" || userInfo === undefined) {
        // 未登录
        return;
    }
    let userId = JSON.parse(Cookies.get("userInfo")).id;

};



