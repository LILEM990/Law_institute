<template>
<!--  <el-form :rules="rules" class="login-container" label-position="left"-->
<!--           label-width="0px" v-loading="loading">-->
<!--    <h3 class="login_title">用户注册</h3>-->
<!--    <el-form-item prop="account">-->
<!--      <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="用户名"></el-input>-->
<!--    </el-form-item>-->
<!--    <el-form-item prop="checkPass">-->
<!--      <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>-->
<!--    </el-form-item>-->
<!--    <el-form-item prop="nickName">-->
<!--      <el-input type="text" v-model="loginForm.nickname" auto-complete="off" placeholder="昵称"></el-input>-->
<!--    </el-form-item>-->
<!--    <el-form-item prop="type">-->
<!--      <el-autocomplete v-model="loginForm.type" :fetch-suggestions="querySearch" @select="handleSelect" placeholder="用户类型" style="width: 100%"></el-autocomplete>-->
<!--    </el-form-item>-->
<!--    <el-form-item style="width: 100%">-->
<!--      <el-button type="primary" @click.native.prevent="submitClick" style="width: 100%">注册</el-button>-->
<!--    </el-form-item>-->
<!--  </el-form>-->
  <el-form :rules="rules" class="login-container" label-position="left"
           v-loading="loading">
    <h3 class="login_title">用户注册</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item prop="checkPass">
      <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item prop="nickName">
      <el-input type="text" v-model="loginForm.nickname" auto-complete="off" placeholder="昵称"></el-input>
    </el-form-item>

    <el-form-item prop="type">
      <el-select v-model="loginForm.type" placeholder="请选择用户类型" style="width: 100%">
        <el-option label="律师" value="律师"></el-option>
        <el-option label="普通用户" value="普通用户"></el-option>
      </el-select>
    </el-form-item>

    <div v-if="isLawyer">
      <el-form-item prop="age">
        <el-input type="text" v-model="loginForm.age" auto-complete="off" placeholder="年龄"></el-input>
      </el-form-item>

      <el-form-item label="性别" prop="gender">
        <el-radio-group v-model="loginForm.gender">
          <el-radio label="男"></el-radio>
          <el-radio label="女"></el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item prop="worktime" label="工作时长（年）">
        <el-input-number type="text" v-model="loginForm.worktime" auto-complete="off"></el-input-number>
      </el-form-item>
      <el-form-item prop="cases" label="成功案例数">
        <el-input-number type="text" v-model="loginForm.cases" auto-complete="off"></el-input-number>
      </el-form-item>

      <el-form-item prop="descrip">
        <el-input type="textarea" v-model="loginForm.descrip" auto-complete="off" placeholder="自我描述"></el-input>
      </el-form-item>

      <el-form-item prop="free" label="是否咨询免费">
        <el-switch v-model="loginForm.free"></el-switch>
      </el-form-item>

      <el-form-item prop="casetype">
        <el-input type="text" v-model="loginForm.casetype" auto-complete="off" placeholder="擅长领域"></el-input>
      </el-form-item>
      <el-form-item prop="company">
        <el-input type="text" v-model="loginForm.company" auto-complete="off" placeholder="所属律师事务所"></el-input>
      </el-form-item>
    </div>


    <el-form-item style="width: 100%">
      <el-button type="primary" @click.native.prevent="submitClick" style="width: 100%">注册</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import axios from 'axios'
import {postRequest} from '../utils/api'
import {putRequest} from '../utils/api'
export default {
  name: "Regist",
  data(){
    return {
      // restaurants: [{ "value": "律师" },{ "value" : "普通用户"}],
      // rules: {
      //   account: [{required: true, message: '请输入用户名', trigger: 'blur'}],
      //   checkPass: [{required: true, message: '请输入密码', trigger: 'blur'}],
      //   nickName: [{required: true, message: '请输入昵称', trigger: 'blur'}],
      //   type: [{required: true, message: '请输入用户类型', trigger: 'blur'}],
      // },
      // checked: true,
      // loginForm: {
      //   username: '',
      //   password: '',
      //   nickname:'',
      //   type:''
      // },
      // loading: false
      rules: {
        account: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        checkPass: [{required: true, message: '请输入密码', trigger: 'blur'}],
        nickName: [{required: true, message: '请输入昵称', trigger: 'blur'}],
        type: [{required: true, message: '请输入用户类型', trigger: 'blur'}],
        age: [{required: true, message: '请输入年龄', trigger: 'blur'}],
        gender: [{required: true, message: '请输入性别', trigger: 'blur'}],
        worktime: [{required: true, message: '请输入工作时长', trigger: 'blur'}],
        cases: [{required: true, message: '请输入成功案例数', trigger: 'blur'}],
        descrip: [{required: true, message: '请输入自我描述', trigger: 'blur'}],
        free: [{required: true, message: '请输入是否免费', trigger: 'blur'}],
        casetype: [{required: true, message: '请输入擅长领域', trigger: 'blur'}],
        company: [{required: true, message: '请输入所属律师事务所', trigger: 'blur'}],
      },
      checked: true,
      loginForm: {
        username: '',
        password: '',
        nickname: '',
        type: '',
        age: null,
        gender: '',
        worktime: 0,
        cases: 0,
        descrip: '',
        free: true,
        casetype: '',
        company: ''
      },
      loading: false,
      alluser: [],
      alllawyer: []
    }

  },
  computed: {
    isLawyer() {
      if (this.loginForm.type === '律师')
        return true
      return false
    }
  },
  created() {
    axios.get('http://localhost:8888/user/findAllUser')
      .then(response => JSON.parse(JSON.stringify(response.data)))
      .then(res => this.alluser = res)
    axios.get('http://localhost:8888/lawyer/findAllLawyer')
      .then(response => JSON.parse(JSON.stringify(response.data)))
      .then(res => this.alllawyer = res)
  },
  // methods: {
  //   submitClick: function () {
  //     //调注册接口
  //     console.log(this.loginForm)
  //     console.log("注册，然后跳到登录页")
  //     let user={
  //       "username": this.loginForm.username,
  //       "password": this.loginForm.password,
  //       "nickname": this.loginForm.nickname,
  //       "type": this.loginForm.type,
  //       "pic":''
  //     }
  //     axios.post('http://localhost:8888/user/addUser',user)
  //     .then(response => {
  //       //console.log(response)
  //       this.$router.replace({path: '/login'});
  //     })
  //
  //   },
  //   querySearch(queryString,cb){
  //     var restaurants = this.restaurants;
  //     var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
  //     //调用callback返回建议列表的数据
  //     cb(results);
  //   },
  //   createFilter(queryString){
  //     return (restaurant) => {
  //       return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
  //     };
  //   },
  //   handleSelect(item){
  //     console.log(item);
  //   }
  // }
  methods: {
    submitClick: function () {
      //调注册接口
      console.log(this.loginForm.username, this.loginForm.password, this.loginForm.nickname, this.loginForm.type)
      console.log("注册，然后跳到登录页")
      let user = {
        "username": this.loginForm.username,
        "password": this.loginForm.password,
        "nickname": this.loginForm.nickname,
        "type": this.loginForm.type,
        "pic": ''
      }
      let lawyer = {
        "lawyer_username": this.loginForm.username,
        "lawyer_password": this.loginForm.password,
        "name": this.loginForm.nickname,
        "age": this.loginForm.age,
        "gender": this.loginForm.gender,
        "worktime": this.loginForm.worktime,
        "cases": this.loginForm.cases,
        "descrip": this.loginForm.descrip,
        "free": this.loginForm.free,
        "casetype": this.loginForm.casetype,
        "pic": '',
        "company": this.loginForm.company
      }
      if (user.type === '普通用户' && this.alluser.filter(a=>a.username===this.loginForm.username).length===0) {
        axios.post('http://localhost:8888/user/addUser', user)
          .then(() => this.$router.push('/myhome'))

      } else if (user.type === '律师' && this.alllawyer.filter(a=>a.lawyer_username===this.loginForm.username).length===0) {
        axios.post('http://localhost:8888/lawyer/addLawyer', lawyer)
          .then(() => this.$router.push('/myhome'))
      } else {
        this.$message.error('用户名或密码已存在');
      }
    },
    querySearch(queryString, cb) {
      var restaurants = this.restaurants;
      var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
      //调用callback返回建议列表的数据
      cb(results);
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    handleSelect(item) {
      console.log(item);
    }
  }
}
</script>

<style scoped>

.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}

.login_remember {
  margin: 0px 0px 35px 0px;
  text-align: left;
}
</style>
