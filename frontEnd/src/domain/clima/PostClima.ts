import { AuthClient } from "../../data/AuthClient";


export async function PostClima(clima) {
    try {
        const authClient = new AuthClient();
        const response = await authClient.postClima(clima);
        return response;
    } catch (error) {
        if (error instanceof SyntaxError) {
            console.log("JSON parsing error: Response body is not valid JSON.");
        } else {
            console.error("postClima error", error);
            throw error;
        }
    }
}
