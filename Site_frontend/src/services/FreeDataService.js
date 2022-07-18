import http from '../http-common'

class FreeDataService {

    getAll(params) {
        return http.get("/frees", {params});
    }

    get(id) {
        return http.get(`/frees/${id}`);
    }

    create(data) {
        return http.post("/frees", data);
    }

    update(id, data) {
        return http.put(`/frees/${id}`, data);
    }

    delete(id) {
        return http.put(`/frees/deletion/${id}`);
    }
}

export default new FreeDataService()







