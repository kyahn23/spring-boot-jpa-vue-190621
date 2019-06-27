<template>
<div>
  <Nav></Nav>
  <form novalidate>
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="text" class="form-control" id="email" v-model="email" placeholder="Enter email" @focus="resetError">
      <div class="validation-errors">
        <div>
      <p v-if="!validation.email.format && validation.email.required">이메일 주소 형식에 어긋납니다</p>
      <p v-if="!validation.email.required">이메일 주소가 입력되지 않았습니다</p>
        </div>
      </div>
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" v-model="password" placeholder="Enter password">
    </div>
    <div class="checkbox">
      <label><input type="checkbox"> Recommon me</label>
    </div>
    <button @click="logincheck()" type="button" class="btn btn-default">Submit</button>
  </form>
  <Footer></Footer>
</div>
</template>

<script>
import Nav from '@/components/common/Nav.vue'
import Footer from'@/components/common/Footer.vue'
import axios from 'axios'


const REGEX_EMAIL = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
const required = val => !!val.trim()

 
export default {
  data () {
    return {
      email: '',
      password: ''
    }
  },
  components: {
    Nav,
    Footer
  },
  methods:{
    resetError () {
      this.error = ''
    },
    logincheck () {
     if (this.email == 'asdf@asdf.com' && this.password == 'asdf') {
       alert('로그인되었습니다.')
       axios.get('customers/count').then(d=>{
        alert(`'success' + ${d.data}`)
      })
      .catch(e=>{
        alert('error')
      })
      //  return this.$router.push('/')
     } 
    },
    submit: function () {
      alert('aaaaa')
      axios.get('customers/count').then(d=>{
        alert('success' + d)
      })
      .catch(e=>{
        alert('error')
      })
    }
  },
  computed: {
    validation () { // email, password 유효성 검사
      return {
        email: {
          required: required(this.email),
          format: REGEX_EMAIL.test(this.email)
          
        },
        password: {
          required: required(this.password)
        }
      }
    },

    valid () {
      const validation = this.validation // 앞서 정의한 validation을 사용해 유효 여부 반환
      const fields = Object.keys(validation)
      let valid = true
      for (let i = 0; i < fields.length; i++) {
        const field = fields[i]
        valid = Object.keys(validation[field])
          .every(key => validation[field][key])
        if (!valid) { break }
      }
      return valid

    },
    

  }
}


</script>

<style scoped>
</style>