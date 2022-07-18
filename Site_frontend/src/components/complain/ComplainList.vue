<template>
  <div class="container mt-3">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by Title" v-model="searchTitle" />
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button" @click="
            page = 1;
          retrieveComplains();
          ">
            Search
          </button>
        </div>
      </div>
    </div>

    <div class="col-md-12">
      <div class="mb-3">
        Items Per Page:
        <select v-model="pageSize" @change="handlePageSizeChange($event)">
          <!-- pageSizes : [3,6,9] -->
          <option v-for="size in pageSizes" :key="size" :value="size">
            {{ size }}
          </option>
        </select>
      </div>


      <b-pagination v-model="page" :total-rows="count" :per-page="pageSize" prev-text="Prev" next-text="Next"
        @change="handlePageChange"></b-pagination>
    </div>

    <table class="table">
      <thead class="table-dark">
        <tr>
          <th scope="col">No</th>
          <th scope="col">Title</th>
          <th scope="col">Content</th>
          <th scope="col">Target Page</th>
          <th scope="col">Writer</th>
          <th scope="col">Actions</th>
        </tr>
      </thead>

      <tbody v-for="(complain, index) in complains" :key="index">
        <tr>
          <td>{{ complain.id }}</td>
          <td>{{ complain.title }}</td>
          <td>{{ complain.content }}</td>
          <td>{{ complain.targetPage }}</td>
          <td>{{ complain.writer }}</td>
          <td>
            <a :href="'/complains/' + complain.id" class="btn btn-primary">
              Edit
            </a>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import ComplainDataService from "@/services/ComplainDataService";

/* eslint-disable */
export default {
  name: "complains",
  data() {
    return {
      complains: [],
      searchTitle: "",
      page: 1,
      count: 0,
      pageSize: 3,

      pageSizes: [3, 6, 9],
    };
  },
  methods: {

    getRequestParams(searchTitle, page, pageSize) {
      let params = {};

      if (searchTitle) {
        params["title"] = searchTitle;
      }

      if (page) {
        params["page"] = page - 1;
      }

      if (pageSize) {
        params["size"] = pageSize;
      }

      return params;
    },

    retrieveComplain() {

      const params = this.getRequestParams(
        this.searchTitle,
        this.page,
        this.pageSize
      );

      ComplainDataService.getAll(params)

        .then((response) => {
          const { complains, totalItems } = response.data;

          this.complains = complains;
          this.count = totalItems;
        })
        .catch((e) => {
          alert(e);
        });
    },

    handlePageChange(value) {

      this.page = value;
      this.retrieveComplain();
    },

    handlePageSizeChange(event) {
      this.pageSize = event.target.value;
      this.page = 1;

      this.retrieveComplain();
    },
  },
  mounted() {
    this.retrieveComplain();
  },
};
</script>
