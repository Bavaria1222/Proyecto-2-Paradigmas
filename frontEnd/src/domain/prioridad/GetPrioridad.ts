import { AuthClient } from "src/data/AuthClient";

async function GetPrioridad() {
  const usersClient = new AuthClient();

  try {
    return await usersClient.getPrioridad();
  } catch (e) {
    console.log(e);
    throw e;
  }
}

export default GetPrioridad;
