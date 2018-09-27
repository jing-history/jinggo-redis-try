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
        currentPageName: ''
    },
    mutations: {
        setCurrentPageName(state, name) {
            state.currentPageName = name;
        }
    }
};

export default app;