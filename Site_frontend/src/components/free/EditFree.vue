<template>

  <div class="container mt-3" v-if="currentFree">

    <div class="mb-3">
      <label for="title" class="form-label">TITLE</label>
      <input type="text" class="form-control" id="title" required name="title" v-model="currentFree.title">
    </div>
    <div class="mb-3">
      <label for="content" class="form-label">CONTENT</label>
      <input type="content" class="form-control" id="content" required name="content" v-model="currentFree.content">
    </div>
    <div class="mb-3">
      <label for="writer" class="form-label">WRITER</label>
      <input type="text" class="form-control" id="writer" required name="writer" v-model="currentFree.writer">
    </div>
    <div class="mb-3">
      <button @click="updateFree" class="btn btn-primary me-3">Update</button>
      <button @click="deleteFree" class="btn btn-danger">Delete</button>
    </div>

    <div class="alert alert-success" role="alert" v-if="message">
      {{ message }}
    </div>
  </div>
</template>

<script>

import FreeDataService from "@/services/FreeDataService";

export default {
  name: "edit-free",
  data() {
    return {
      currentFree: null,
      message: ''
    }
  },
  methods: {

    getFree(id) {

      FreeDataService.get(id)

        .then(response => {
          this.currentFree = response.data;
        })

        .catch(e => {
          alert(e);
        })
    },

    updateFree() {

      FreeDataService
        .update(this.currentFree.id, this.currentFree)

        .then(() => {
          this.message = alert('The free content was updated successfully!');
          this.$router.push({ name: 'frees' });
        })

        .catch(e => {
          alert(e);
        })
    },

    deleteFree() {

      FreeDataService.delete(this.currentFree.id)

        .then(() => {
          this.message = alert('The free content was deleted successfully!');
          this.$router.push({ name: 'frees' });
        })
        .catch(e => {
          alert(e);
        })
    }
  },

  mounted() {
    this.getFree(this.$route.params.id);
  }
}
</script>