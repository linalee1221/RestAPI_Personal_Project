<template>

  <div class="container mt-3" v-if="currentComplain">

    <div class="mb-3">
      <label for="title" class="form-label">TITLE</label>
      <input type="text" class="form-control" id="title"
             required name="title" v-model="currentComplain.title">
    </div>
    <div class="mb-3">
      <label for="content" class="form-label">CONTENT</label>
      <input type="content" class="form-control" id="content"
             required name="content" v-model="currentComplain.content">
    </div>
    <div class="mb-3">
      <label for="targetPage" class="form-label">TARGET PAGE</label>
      <input type="targetPage" class="form-control" id="targetPage"
             required name="targetPage" v-model="currentComplain.targetPage">
    </div>
    <div class="mb-3">
      <label for="writer" class="form-label">WRITER</label>
      <input type="text" class="form-control" id="writer"
             required name="writer" v-model="currentComplain.writer">
    </div>
    <div class="mb-3">
      <button @click="updateComplain"
              class="btn btn-primary me-3">Update</button>
      <button @click="deleteComplain"
              class="btn btn-danger">Delete</button>
    </div>

    <div class="alert alert-success"
         role="alert"
         v-if="message"
    >
      {{ message }}
    </div>
  </div>
</template>

<script>

import ComplainDataService from "@/services/ComplainDataService";

export default {
  name: "edit-complain",
  data() {
    return {
      currentComplain: null,
      message: ''
    }
  },
  methods: {

    getComplain(id) {

      ComplainDataService.get(id)

          .then(response => {
            this.currentComplain = response.data;
          })

          .catch(e => {
            alert(e);
          })
    },

    updateComplain() {

      ComplainDataService
          .update(this.currentComplain.id, this.currentComplain )

          .then(() => {
            this.message = alert('The complain was updated successfully!');
            this.$router.push({ name: 'complains'});
          })

          .catch(e => {
            alert(e);
          })
    },

    deleteComplain() {

      ComplainDataService.delete(this.currentComplain.id)

          .then(() => {
            this.message = alert('The complain was deleted successfully!');
            this.$router.push({ name: 'complains'});
          })
          .catch(e => {
            alert(e);
          })
    }
  },

  mounted() {
    this.getComplain(this.$route.params.id);
  }
}
</script>