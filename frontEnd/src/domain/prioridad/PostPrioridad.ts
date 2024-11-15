import { AuthClient } from "../../data/AuthClient";


export async function PostPrioridad(empleadoDto) {
    try {
        const authClient = new AuthClient();
        const response = await authClient.postPrioridad(empleadoDto);
        return response;
    } catch (error) {
        if (error instanceof SyntaxError) {
            console.log("JSON parsing error: Response body is not valid JSON.");
        } else {
            console.error("postPrioridad error", error);
            throw error;
        }
    }
}
