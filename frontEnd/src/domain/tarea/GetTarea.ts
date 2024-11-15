import { AuthClient } from "src/data/AuthClient";

async function GetTarea() {
  const usersClient = new AuthClient();

  try {
    return await usersClient.getTarea();
  } catch (e) {
    console.log(e);
    throw e;
  }
}

export default GetTarea;