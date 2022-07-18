<template>
  <div class="container mt-3">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by Title" v-model="searchTitle" />
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button" @click="
            page = 1;
          retrieveFrees();
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

    <table class="table table-hover">
      <thead class="table-dark">
        <tr>
          <th scope="col">No</th>
          <th scope="col">Title</th>
          <th scope="col">Content</th>
          <th scope="col">Writer</th>
          <th scope="col">Actions</th>
        </tr>
      </thead>

      <tbody v-for="(free, index) in frees" :key="index">
        <tr>
          <td>{{ free.id }}</td>
          <td>{{ free.title }}</td>
          <td>{{ free.content }}</td>
          <td>{{ free.writer }}</td>
          <td>
            <a :href="'/frees/' + free.id" class="btn btn-primary">
              Edit
            </a>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import FreeDataService from "@/services/FreeDataService";

/* eslint-disable */
export default {
  name: "frees",
  data() {
    return {
      frees: [],
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

    retrieveFree() {

      const params = this.getRequestParams(
        this.searchTitle,
        this.page,
        this.pageSize
      );

      FreeDataService.getAll(params)

        .then((response) => {
          const { frees, totalItems } = response.data;

          this.frees = frees;
          this.count = totalItems;
        })
        .catch((e) => {
          alert(e);
        });
    },

    handlePageChange(value) {

      this.page = value;
      this.retrieveFree();
    },

    handlePageSizeChange(event) {
      this.pageSize = event.target.value;
      this.page = 1;

      this.retrieveFree();
    },
  },
  mounted() {
    this.retrieveFree();
  },
};
</script>
