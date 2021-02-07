import http from "../http-common";

class TutorialDataService {
  
  getAll() {
    return http.get("/product/all");
  }

  get(id) {
    return http.get(`/product/${id}`);
  }

  insert(data) {
    return http.post("/product", data);
  }

  update(id, data) {
    return http.put(`/product/${id}`, data);
  }

  delete(id) {
    return http.delete(`/product/${id}`);
  }

  createSeller(data){
    return http.post("/seller", data);
  }

}

export default new TutorialDataService();