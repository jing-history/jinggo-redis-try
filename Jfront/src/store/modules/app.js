/**
 * Created by gz12 on 2018-09-27.
 */
import { otherRouter } from '@/router/router';
import { router } from '@/router/index';
import Util from '@/libs/util';
import Cookies from 'js-cookie';
import Vue from 'vue';

const app = {
    state: {
        cachePage: [],
        lang: '',
        isFullScreen: false,
        openedSubmenuArr: [], // 要展开的菜单数组
        menuTheme: 'dark', // 主题
        themeColor: '',
        pageOpenedList: [{
            title: '首页',
            path: '',
            name: 'home_index'
        }],
        currentPageName: '',
        currentPath: [
            {
                title: '首页',
                path: '',
                name: 'home_index'
            }
        ],
        // 面包屑数组
        menuList: [],
        routers: [
            otherRouter
        ],
        tagsList: [...otherRouter.children],
        messageCount: 0,
        // 在这里定义你不想要缓存的页面的name属性值(参见路由配置router.js)
        dontCache: ['test', 'test']
    },
    mutations: {
        // 动态添加主界面路由，需要缓存
        updateAppRouter(state, routes) {
            state.routers.push(...routes);
            router.addRoutes(routes);
        },
        // 动态添加全局路由，不需要缓存
        updateDefaultRouter(state, routes) {
            router.addRoutes(routes);
        },
        setTagsList(state, list) {
            state.tagsList.push(...list);
        },
        updateMenulist(state, routes) {
            state.menuList = routes;
        },
        setCurrentPageName(state, name) {
            state.currentPageName = name;
        },
        setAvatarPath(state, path) {
            localStorage.avatorImgPath = path;
        },
        setOpenedList(state) {
            state.pageOpenedList = localStorage.pageOpenedList ? JSON.parse(localStorage.pageOpenedList) : [otherRouter.children[0]];
        },
    }
};

export default app;