<template>
  <div class="container mt-3">
    <!-- Todo : search bar 수정 시작 -->
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by Email" v-model="searchEmail" />
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button" @click="
            page = 1;
          retrieveCustomers();
          ">
            Search
          </button>
        </div>
      </div>
    </div>
    <!-- Todo : search bar 수정 끝 -->

    <!-- Todo : page bar 수정 시작 -->
    <div class="col-md-12">
      <div class="mb-3">
        Items per Page:
        <select v-model="pageSize" @change="handlePageSizeChange($event)">
          <!-- pageSizes : [3,6,9] -->
          <option v-for="size in pageSizes" :key="size" :value="size">
            {{ size }}
          </option>
        </select>
      </div>

      <!-- page : 현재 페이지, count = 총 데이터 건수, paseSize : 1페이지 당 건수 -->

      <b-pagination v-model="page" :total-rows="count" :per-page="pageSize" prev-text="Prev" next-text="Next"
        @change="handlePageChange"></b-pagination>
    </div>
    <!-- Todo : page bar 수정 끝 -->

    <table class="table table-hover">
      <thead class="table-dark">
        <tr>
          <th scope="col">First Name</th>
          <th scope="col">Last Name</th>
          <th scope="col">Email</th>
          <th scope="col">Phone</th>
          <th scope="col">Actions</th>
        </tr>
      </thead>
      <!--      조회 데이터 생성 부분-->
      <tbody v-for="(customer, index) in customers" :key="index">
        <tr>
          <td>{{ customer.firstName }}</td>
          <td>{{ customer.lastName }}</td>
          <td>{{ customer.email }}</td>
          <td>{{ customer.phone }}</td>
          <td>
            <a :href="'/customers/' + customer.id" class="btn btn-primary">
              Edit
            </a>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import CustomerDataService from "@/services/CustomerDataService";

/* eslint-disable */
export default {
  name: "customers",
  data() {
    // 변수의 초기화 하기
    return {
      customers: [],
      // Todo : title => searchEmail 로 변경
      searchEmail: "",

      // Todo : 아래 변수 추가
      page: 1,
      count: 0,
      pageSize: 3,

      pageSizes: [3, 6, 9],
    };
  },
  methods: {
    // Todo : getRequestParams 추가
    // 역할 : springboot로 매개변수(parameter)들을 전송
    // 웹 매개변수 : http://localhost:8080/customers?page=1&pageSize=3
    getRequestParams(searchEmail, page, pageSize) {
      let params = {};

      // searchEmail값이 있으면 매개변수로 전송
      if (searchEmail) {
        params["email"] = searchEmail;
      }

      if (page) {
        params["page"] = page - 1;
      }

      if (pageSize) {
        params["size"] = pageSize;
      }

      // 3개가 다 들어왔다면 아래 형태로 springboot로 전송함
      // params {
      //  "email":"les911221@gmail.com",
      //  "page":0,
      //  "size":3
      // }

      return params;
    },
    // 모든 회원 조회 서비스 호출
    retrieveCustomers() {
      // Todo: getRequestParams 호출 추가
      const params = this.getRequestParams(
        this.searchEmail,
        this.page,
        this.pageSize
      );

      // Todo : getAll() -> getAll(params)
      // axios로 spring에 모든 회원 조회 요청
      CustomerDataService.getAll(params)
        //  성공하면 then으로 서버(spring) 데이터(response.data)가 들어옴
        .then((response) => {
          // Todo : 아래 수정
          // this.customers = response.data;
          const { customers, totalItems } = response.data;

          // springboot에서 받은 객체 정보
          this.customers = customers;
          // springboot에서 받은 페이지 총 건수 정보
          this.count = totalItems;
        })
        //  실패하면 catch로 에러메세지가 들어옴
        .catch((e) => {
          alert(e);
        });
    },

    // Todo : 신규 메소드 2개 추가
    // 현재 페이지를 다른 페이지 번호로 변경 시 호출되는 메소드
    handlePageChange(value) {
      // 현재페이지 변경
      this.page = value;
      // 변경 후 재조회
      this.retrieveCustomers();
    },
    // 페이지 사이즈(3,6,9) 변경 시 호출되는 메소드
    handlePageSizeChange(event) {
      this.pageSize = event.target.value; // select박스의 값
      this.page = 1;
      // 변경 후 재조회
      this.retrieveCustomers();
    },
  },
  //화면이 뜨자마자 실행되는 이벤트(모든 회원조회가 실행됨)
  mounted() {
    this.retrieveCustomers();
  },
};
</script>
