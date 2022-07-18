<template>
  <div class="container mt-3">
    <div v-if="!submitted">
      <div class="mb-3">
        <label for="title" class="form-label">Title</label>
        <input type="text" class="form-control" id="title" required name="title" v-model="complain.title">
      </div>
      <div class="mb-3">
        <label for="content" class="form-label">Content</label>
        <input type="text" class="form-control" id="content" required name="content" v-model="complain.content">
      </div>
      <div class="mb-3">
        <label for="targetPage" class="form-label">Target_Page</label>
        <input type="text" class="form-control" id="targetPage" required name="targetPage"
          v-model="complain.targetPage">
      </div>
      <div class="mb-3">
        <label for="writer" class="form-label">Writer</label>
        <input type="text" class="form-control" id="writer" required name="writer" v-model="complain.writer">
      </div>
      <div class="mb-3">
        <button @click="saveComplain" class="btn btn-primary">Submit</button>
      </div>
    </div>

    <div v-else>
      <div class="alert alert-success" role="alert">
        Save complain successfully!
      </div>
      <button @click="newComplain" class="btn btn-primary">Add New Complain</button>
    </div>
  </div>
</template>

<script>
import ComplainDataService from "@/services/ComplainDataService";

export default {
  name: "add-complain",
  data() {
    return {
      complain: {
        id: null,
        title: "",
        content: "",
        targetPage: "",
        writer: ""
      },
      submitted: false
    }
  },
  methods: {
    saveComplain() {
      var data = {
        title: this.complain.title,
        content: this.complain.content,
        targetPage: this.complain.targetPage,
        writer: this.complain.writer
      }

      ComplainDataService.create(data)
        .then(response => {
          this.complain.id = response.data.id;
          this.submitted = true;
          this.message = alert('The complain was added successfully!');
          this.$router.push({ name: 'complains' });
        })
        .catch(e => {
          alert(e);
        })

    },
    newComplain() {
      this.submitted = false;
      this.complains = {};
    }
  }
}
</script>








