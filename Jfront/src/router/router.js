/**
 * Created by gz12 on 2018-09-26.
 */

// 不作为Main组件的子页面展示的页面单独写，如下
export const loginRouter = {
    path: '/login',
    name: 'login',
    meta: {
        title: '登录 - X-Boot前后端分离开发平台 '
    },
    component: () => import('@/views/login.vue')
};

// 所有上面定义的路由都要写在下面的routers里
export const routers = [
    loginRouter
];