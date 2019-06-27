import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Calculator from '@/components/basic/Calculator'
import JoinForm from '@/components/member/JoinForm'
import LoginForm from '@/components/member/LoginForm'
import MemberList from '@/components/member/MemberList'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {path: '/memberlist', name: 'memberlist', component: MemberList},
    {path: '/loginform', name: 'loginform', component: LoginForm},
    {path: '/', name: 'home', component: Home},
    {path: '/calculator', name: 'calculator', component: Calculator},
    {path: '/joinform', name: 'joinform', component: JoinForm}
  ]
})
