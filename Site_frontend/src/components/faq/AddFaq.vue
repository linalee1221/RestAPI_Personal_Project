<template>
  <div class="container mt-3">
    <div v-if="!submitted">
      <div class="mb-3">
        <label for="title" class="form-label">Title</label>
        <input type="text" class="form-control" id="title" required name="title" v-model="faq.title">
      </div>
      <div class="mb-3">
        <label for="content" class="form-label">Content</label>
        <input type="text" class="form-control" id="content" required name="content" v-model="faq.content">
      </div>
      <div class="mb-3">
        <button @click="saveFaq" class="btn btn-primary">Submit</button>
      </div>
    </div>

    <div v-else>
      <div class="alert alert-success" role="alert">
        Save FAQ Successfully!
      </div>
      <button @click="newFaq" class="btn btn-primary">Add New FAQ</button>
    </div>
  </div>
</template>

<script>
import FaqDataService from "@/services/FaqDataService";

export default {
  name: "add-faq",
  data() {
    return {
      faq: {
        id: null,
        title: "",
        content: ""
      },
      submitted: false
    }
  },
  methods: {
    saveFaq() {
      var data = {
        title: this.faq.title,
        content: this.faq.content
      }

      FaqDataService.create(data)
        .then(response => {
          this.faq.id = response.data.id;
          this.submitted = true;
          this.message = alert('The FAQ was added successfully!');
          this.$router.push({ name: 'faqs' });
        })
        .catch(e => {
          alert(e);
        })

    },
    newFaq() {
      this.submitted = false;
      this.faq = {};
    }
  }
}
</script>








