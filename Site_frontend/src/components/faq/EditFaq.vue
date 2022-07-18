<template>

  <div class="container mt-3" v-if="currentFaq">

    <div class="mb-3">
      <label for="title" class="form-label">TITLE</label>
      <input type="text" class="form-control" id="title"
             required name="title" v-model="currentFaq.title">
    </div>
    <div class="mb-3">
      <label for="content" class="form-label">CONTENT</label>
      <input type="content" class="form-control" id="content"
             required name="content" v-model="currentFaq.content">
    </div>
    <div class="mb-3">
      <button @click="updateFaq"
              class="btn btn-primary me-3">Update</button>
      <button @click="deleteFaq"
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

import FaqDataService from "@/services/FaqDataService";

export default {
  name: "edit-faq",
  data() {
    return {
      currentFaq: null,
      message: ''
    }
  },
  methods: {

    getFaq(id) {

      FaqDataService.get(id)

          .then(response => {
            this.currentFaq = response.data;
          })

          .catch(e => {
            alert(e);
          })
    },

    updateFaq() {

      FaqDataService
          .update(this.currentFaq.id, this.currentFaq )

          .then(() => {
            this.message = alert('The FAQ was updated successfully!');
            this.$router.push({ name: 'faqs'});
          })

          .catch(e => {
            alert(e);
          })
    },

    deleteFaq() {

      FaqDataService.delete(this.currentFaq.id)

          .then(() => {
            this.message = alert('The FAQ was deleted successfully!');
            this.$router.push({ name: 'faqs'});
          })
          .catch(e => {
            alert(e);
          })
    }
  },

  mounted() {
    this.getFaq(this.$route.params.id);
  }
}
</script>