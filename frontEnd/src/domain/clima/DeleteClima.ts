import { AuthClient } from "src/data/AuthClient";

async function DeleteClima(id: string): Promise<void> {
  const usersClient = new AuthClient();
  try {
    await usersClient.deleteClima(id);
  } catch (e) {
    console.error('Error borrando clima:', e);
    throw e;
  }
}

export default DeleteClima;