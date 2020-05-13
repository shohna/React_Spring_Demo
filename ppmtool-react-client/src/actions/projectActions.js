import axios from "axios";
import { GET_ERRORS } from "./types";
import Dashboard from "../components/Dashboard";

export const createProject = (Project, history) => async (dispatch) => {
  try {
    const res = await axios.post("http://localhost:8080/api/project", Project);
    history.push("/dashboard");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};
