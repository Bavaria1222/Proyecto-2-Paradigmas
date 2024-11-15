import { AuthClient } from "../../data/AuthClient";

async function PatchPrioridad(id: string, updateUserDto) {
  try {
    const authClient = new AuthClient();
    await authClient.patchPrioridad(id, updateUserDto);
  } catch (error) {
    if (error instanceof SyntaxError) {
      console.log("JSON parsing error: Response body is not valid JSON.");
    } else {
      console.error("patchPrioridad error", error);
      throw error;
    }
  }
}

export default PatchPrioridad;
