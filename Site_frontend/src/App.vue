<template>
  <div id="app">
    <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
      <!--      m(margin), l(left), r(right), t(top), b(bottom), -1~5-->
      <a class="navbar-brand mr-5" href="/">
        <img
          src="@/assets/img/cherry-blossom.png"
          width="30"
          height="30"
          alt=""
        />
      </a>
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarNavDropdown"
        aria-controls="navbarNavDropdown"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
          <!-- dropdown #1 시작 -->
          <li class="nav-item dropdown mr-4">
            <a
              class="nav-link"
              href="/home"
              id="navbarDropdownMenuLink"
            >
              HOME
            </a>
            <div
              class="dropdown-menu"
              aria-labelledby="navbarDropdownMenuLink"
            ></div>
          </li>
          <!-- dropdown #1 끝 -->

          <!-- dropdown #2 시작 -->
          <li class="nav-item dropdown mr-4">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdownMenuLink"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Tutorial Menu
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <router-link to="/tutorials" class="dropdown-item">
                Tutorials List
              </router-link>
              <router-link to="/add" class="dropdown-item">
                Add New Tutorials
              </router-link>
              <router-link to="/upload" class="dropdown-item">
                Upload Image
              </router-link>
            </div>
          </li>
          <!-- dropdown #2 끝 -->

          <!-- dropdown #3 시작 -->
          <li class="nav-item dropdown mr-4">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdownMenuLink"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Customers Manager
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <router-link to="/customers" class="dropdown-item">
                Customer List
              </router-link>
              <router-link to="/add-customer" class="dropdown-item">
                Add New Customer
              </router-link>
            </div>
          </li>
          <!-- dropdown #3 끝 -->

          <!-- dropdown #4 시작 -->
          <li class="nav-item dropdown mr-4">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdownMenuLink"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Complain Manager
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <router-link to="/complains" class="dropdown-item">
                Complain List
              </router-link>
              <router-link to="/add-complain" class="dropdown-item">
                Add New Complain
              </router-link>
            </div>
          </li>
          <!-- dropdown #4 끝 -->

          <!-- dropdown #5 시작 -->
          <li class="nav-item dropdown mr-4">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdownMenuLink"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Free Board
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <router-link to="/frees" class="dropdown-item">
                Free Board List
              </router-link>
              <router-link to="/add-free" class="dropdown-item">
                Add New Free Content
              </router-link>
            </div>
          </li>
          <!-- dropdown #5 끝 -->

          <!-- dropdown #6 시작 -->
          <li class="nav-item dropdown mr-4">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdownMenuLink"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Q&A
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <router-link to="/faqs" class="dropdown-item">
                Q&A List
              </router-link>
              <router-link to="/add-faq" class="dropdown-item">
                Add New Q&A
              </router-link>
            </div>
          </li>
          <!-- dropdown #6 끝 -->
        </ul>
      </div>

      <!--      유저가 로그인 안하면 아래 메뉴가 보임 : 로그인 메뉴가 보임-->
      <div v-if="!currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/register" class="nav-link">
            <!--            웹 아이콘 추가 : user -->
            <font-awesome-icon icon="user-plus"></font-awesome-icon>
            Sign Up
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/login" class="nav-link">
            <!--            웹 아이콘 추가 : user -->
            <font-awesome-icon icon="sign-in-alt"></font-awesome-icon>
            Login
          </router-link>
        </li>
      </div>

      <!--      유저가 로그인 하면 아래 메뉴가 보임 : 로그아웃 메뉴가 보임 -->
      <div v-if="currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/profile" class="nav-link">
            <!--            웹 아이콘 추가 : user -->
            <font-awesome-icon icon="user"></font-awesome-icon>
            {{ currentUser.username }}
          </router-link>
        </li>
        <li class="nav-item">
          <a href @click.prevent="logOut" class="nav-link">
            <!--            웹 아이콘 추가 : LogOut -->
            <font-awesome-icon icon="sign-out-alt"></font-awesome-icon>
            LogOut
          </a>
        </li>
      </div>
    </nav>

    <!-- <div class="container mt-3"> -->
    <!-- 위의 router-link 있는 메뉴 화면이 아래위치에 표시 -->
    <router-view></router-view>
    <!-- </div> -->
  </div>
</template>

<script>
export default {
  // html에서 변수처럼 호출 : 뒤에 () 붙이면 안됨 예) currentUser
  // 속도는 computed 빠름
  computed: {
    currentUser() {
      // 공유저장소의 전역변수(공유변수 : user )
      // 자동으로 로그인되었으면 loggedIn = true, user객체 있음
      // 아니면 loggedIn = false, user객체 = null
      return this.$store.state.auth.user;
      // return true; // 테스트용
    },
  },
  // html에서 함수처럼 호출 : 뒤에 () 붙음 예) logOut(), logOut
  methods: {
    logOut() {
      // dispatch 호출하는 메소드 : actions 있는 메소드를 호출함
      this.$store.dispatch("auth/logout");
      //   로그아웃 후 이동할 페이지 지정 : login 페이지
      this.$router.push("/login");
    },
  },
};
</script>
