<template>
  <div class="container mt-3">
    <div v-if="!submitted">
      <div class="mb-3">
        <label for="title" class="form-label">Title</label>
        <input type="text" class="form-control" id="title" required name="title" v-model="free.title">
      </div>
      <div class="mb-3">
        <label for="content" class="form-label">Content</label>
        <input type="text" class="form-control" id="content" required name="content" v-model="free.content">
      </div>
      <div class="mb-3">
        <label for="writer" class="form-label">Writer</label>
        <input type="text" class="form-control" id="writer" required name="writer" v-model="free.writer">
      </div>
      <div class="mb-3">
        <button @click="saveFree" class="btn btn-primary">Submit</button>
      </div>
    </div>

    <div v-else>
      <div class="alert alert-success" role="alert">
        Save Free Content Successfully!
      </div>
      <button @click="newFree" class="btn btn-primary">Add New Free Content</button>
    </div>
  </div>
</template>

<script>
import FreeDataService from "@/services/FreeDataService";

export default {
  name: "add-free",
  data() {
    return {
      free: {
        id: null,
        title: "",
        content: "",
        writer: ""
      },
      submitted: false
    }
  },
  methods: {
    saveFree() {
      var data = {
        title: this.free.title,
        content: this.free.content,
        writer: this.free.writer
      }

      FreeDataService.create(data)
        .then(response => {
          this.free.id = response.data.id;
          this.submitted = true;
          this.message = alert('The free content was added successfully!');
          this.$router.push({ name: 'frees' });
        })
        .catch(e => {
          alert(e);
        })

    },
    newFree() {
      this.submitted = false;
      this.free = {};
    }
  }
}
</script>








