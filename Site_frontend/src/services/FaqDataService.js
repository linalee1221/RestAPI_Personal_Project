import http from '../http-common'

class FaqDataService {

    getAll(params) {
        return http.get("/faqs", {params});
    }

    get(id) {
        return http.get(`/faqs/${id}`);
    }

    create(data) {
        return http.post("/faqs", data);
    }

    update(id, data) {
        return http.put(`/faqs/${id}`, data);
    }

    delete(id) {
        return http.put(`/faqs/deletion/${id}`);
    }
}

export default new FaqDataService()







