<template>
    <Row type="flex" justify="center" align="middle" class="login" @keydown.enter.native="submitLogin">
        <Col :xs="{span:22}" style="width: 368px;">
        <Row class="header">
            <img src="../assets/xboot.png" width="220px" />
            <div class="description">X-Boot 是很不错的Web前后端分离架构开发平台</div>
        </Row>

        <Alert type="error" show-icon v-if="error">{{errorMsg}}</Alert>

        <Row class="login-form" v-if="!socialLogining">
            <Tabs v-model="tabName">
                <TabPane label="账户密码登录" name="username" icon="md-person">
                    <Form ref="usernameLoginForm" :model="form" :rules="rules" class="form">
                        <FormItem prop="username">
                            <Input v-model="form.username" prefix="ios-contact" size="large" clearable placeholder="请输入用户名" autocomplete="off" />
                        </FormItem>
                        <FormItem prop="password">
                            <Input type="password" v-model="form.password" prefix="ios-lock" size="large" clearable placeholder="请输入密码" autocomplete="off" />
                        </FormItem>
                    </Form>
                </TabPane>
                <TabPane label="手机号登录" name="mobile" icon="ios-phone-portrait">
                    <Form ref="mobileLoginForm" :model="form" :rules="rules" class="form">
                        <FormItem prop="mobile">
                            <Input v-model="form.mobile" prefix="ios-phone-portrait" size="large" clearable placeholder="请输入手机号" />
                        </FormItem>
                        <FormItem prop="code" :error="errorCode">
                            <Row type="flex" justify="space-between" class="code-row-bg">
                                <Input v-model="form.code" prefix="ios-mail-outline" size="large" clearable placeholder="请输入短信验证码" :maxlength="maxLength" class="input-verify" />
                                <Button size="large" :loading="sending" @click="sendVerify" v-if="!sended" class="send-verify">
                                    <span v-if="!sending">获取验证码</span>
                                    <span v-else>发送中</span>
                                </Button>
                                <Button size="large" disabled v-if="sended" class="count-verify">{{countButton}}</Button>
                            </Row>
                        </FormItem>
                    </Form>
                </TabPane>
            </Tabs>

            <Row type="flex" justify="space-between" class="code-row-bg">
                <Checkbox v-model="saveLogin" size="large">自动登录</Checkbox>
                <a class="forget-pass" @click="showAccount">忘记密码</a>
            </Row>
            <Row>
                <Button class="login-btn" type="primary" size="large" :loading="loading" @click="submitLogin" long>
                    <span v-if="!loading">登录</span>
                    <span v-else>登录中...</span>
                </Button>
            </Row>
            <Row type="flex" justify="space-between" class="code-row-bg other-login">
                <div class="other-way icons">
                    其它方式登录
                    <div class="other-icon" @click="toGithubLogin">
                        <icon scale="1.1" name="brands/github"></icon>
                    </div>
                    <div class="other-icon" @click="toQQLogin">
                        <icon name="brands/qq"></icon>
                    </div>
                    <div class="other-icon" @click="toWeiboLogin">
                        <icon scale="1.2" name="brands/weibo"></icon>
                    </div>
                    <div class="other-icon" @click="toWeixinLogin">
                        <icon scale="1.2" name="brands/weixin"></icon>
                    </div>
                </div>
                <router-link to="/regist"><a class="forget-pass">注册账户</a></router-link>
            </Row>
        </Row>

        <Row class="foot">
            <Row type="flex" justify="space-around" class="code-row-bg help">
                <a class="item" href="https://github.com/Exrick/x-boot" target="_blank">帮助</a>
                <a class="item" href="https://github.com/Exrick/x-boot" target="_blank">隐私</a>
                <a class="item" href="https://github.com/Exrick/x-boot" target="_blank">条款</a>
            </Row>
            <Row type="flex" justify="center" class="code-row-bg copyright">
                Copyright © 2018-Present <a href="http://exrick.cn" target="_blank" style="margin:0 5px;">Exrick</a> 版权所有
            </Row>
        </Row>
        </Col>
    </Row>
</template>

<script>
    import Cookies from "js-cookie";
    import util from "@/libs/util.js";
    export default {
        data(){
            const validateMobile = (rule, value, callback) => {
                var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
                if (!reg.test(value)) {
                    callback(new Error("手机号格式错误"));
                } else {
                    callback();
                }
            };
            return {
                form: {
                    username: "test或test2 可注册",
                    password: "123456",
                    mobile: "捐赠获取完整版功能",
                    code: ""
                },
                rules: {
                    username: [
                        {
                            required: true,
                            message: "账号不能为空",
                            trigger: "blur"
                        }
                    ],
                    password: [
                        {
                            required: true,
                            message: "密码不能为空",
                            trigger: "blur"
                        }
                    ],
                    mobile: [
                        {
                            required: true,
                            message: "手机号不能为空",
                            trigger: "blur"
                        },
                        {
                            validator: validateMobile,
                            trigger: "blur"
                        }
                    ]
                }
            }
        }
    };
</script>

<style lang="less">
    @import "./login.less";
</style>