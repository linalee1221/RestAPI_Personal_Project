<template>
  <!--  부트스트랩 : container 박스형태로 contents를 보여줌 -->
  <div class="container">
    <header class="jumbotron">
      <h3>
        <strong>{{ currentUser.username }}</strong> Profile
      </h3>
    </header>

    <p>
      <strong>Id</strong>
      {{ currentUser.id }}
    </p>
    <p>
      <strong>Email</strong>
      {{ currentUser.email }}
    </p>
  </div>
</template>

<script>
/* eslint-disable */
export default {
  name: "Profile",
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
      // return true; // 화면 디자인 테스트용
    }
  },
  //  화면이 뜨자마자 실행되는 이벤트
  mounted() {
    //  사용자가 로그인하지 않은 경우 로그인 페이지로 강제 이동
    if (!this.currentUser) {
      //  강제 login 페이지로 이동시킴
      this.$router.push("/login");
    }
  }
}
</script>

<style scoped>
</style>