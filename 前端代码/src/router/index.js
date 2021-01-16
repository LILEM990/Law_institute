import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login'
import ArticleList from '../components/ArticleList'
import CateMana from '../components/CateMana'
import PostArticle from '../components/PostArticle'
import UserMana from '../components/UserMana'
import BlogDetail from '../components/BlogDetail'
import Regist from "../components/Regist";
import MyHome from "../components/MyHome";
import Knowledge from "../components/Knowledge";
import UserHome from "../components/UserHome";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '注册',
      hidden: true,
      hiden:true,
      component: Regist
    },{
      path: '/myhome',
      name: '管理员主页',
      hidden: true,
      hiden:true,
      component: MyHome
    },{
      path: '/userhome',
      name: '用户主页',
      hidden: true,
      hiden:true,
      component: UserHome
    },{
      path: '/knowledge',
      name:'法律常识',
      hidden: true,
      hiden:false,
      component: Knowledge
    }, {
      path: '/myhome',
      component: MyHome,
      name: '法律常识',
      children: [
        {
          path: '/knowledge',
          iconCls: 'fa fa-file-text-o',
          name: '法律常识',
          component: Knowledge
        }
      ]
    },{
      path: '/login',
      name: '登录',
      hidden: true,
      hiden:true,
      component: Login
    },{
      path: '/myhome',
      component: MyHome,
      name: '订单管理',
      iconCls: 'fa fa-reorder',
      children: [
        {
          path: '/articleList',
          name: '订单列表',
          component: ArticleList,
          meta: {
            keepAlive: true
          }
        }, {
          path: '/postArticle',
          name: '发表订单',
          component: PostArticle,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/blogDetail',
          name: '博客详情',
          component: BlogDetail,
          hidden: true,
          hiden:true,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/editBlog',
          name: '编辑博客',
          component: PostArticle,
          hidden: true,
          hiden:true,
          meta: {
            keepAlive: false
          }
        }
      ]
    }, {
      path: '/myhome',
      component: MyHome,
      name: '用户管理',
      children: [
        {
          path: '/user',
          iconCls: 'fa fa-user-o',
          name: '用户管理',
          component: UserMana
        }
      ]
    }, {
      path: '/myhome',
      component: MyHome,
      name: '分类管理',
      children: [
        {
          path: '/cateMana',
          iconCls: 'fa fa-bar-chart',
          name: '分类管理',
          component: CateMana
        }
      ]
    },
  ]
})
