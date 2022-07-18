import http from '../http-common'

class ComplainDataService {

    getAll(params) {
        return http.get("/complains", {params});
    }

    get(id) {
        return http.get(`/complains/${id}`);
    }

    create(data) {
        return http.post("/complains", data);
    }

    update(id, data) {
        return http.put(`/complains/${id}`, data);
    }

    delete(id) {
        return http.put(`/complains/deletion/${id}`);
    }
}

export default new ComplainDataService()







