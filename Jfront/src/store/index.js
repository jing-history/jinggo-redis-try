/**
 * Created by gz12 on 2018-09-26.
 */
import Vue from 'vue';
import Vuex from 'vuex';

import app from './modules/app';

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        // 状态

    },
    mutations: {
        // 改变方法
    },
    actions: {

    },
    modules: {
        app
    }
});

export default store;
