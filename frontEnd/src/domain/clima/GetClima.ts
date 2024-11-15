import { AuthClient } from "src/data/AuthClient";

async function GetClima() {
  const usersClient = new AuthClient();

  try {
    return await usersClient.getClima();
  } catch (e) {
    console.log(e);
    throw e;
  }
}

export default GetClima;