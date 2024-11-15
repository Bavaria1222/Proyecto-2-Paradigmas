import { AuthClient } from "src/data/AuthClient";

async function GetDiaSemana() {
  const usersClient = new AuthClient();

  try {
    return await usersClient.getDiaSemana();
  } catch (e) {
    console.log(e);
    throw e;
  }
}

export default GetDiaSemana;