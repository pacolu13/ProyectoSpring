// services/ApiClient.ts
type HttpMethod = "GET" | "POST" | "PUT" | "PATCH" | "DELETE";

interface RequestOptions<TBody = unknown> {
  url: string;
  method?: HttpMethod;
  body?: TBody;
  headers?: Record<string, string>;
}

interface ApiResponse<TResponse> {
  data: TResponse | null;
  error: string | null;
  ok: boolean;
}

export class ApiClient {
  private baseUrl: string;
  private defaultHeaders: Record<string, string>;

  constructor(baseUrl = "", defaultHeaders: Record<string, string> = {}) {
    this.baseUrl = baseUrl;
    this.defaultHeaders = {
      "Content-Type": "application/json",
      ...defaultHeaders,
    };
  }

  async request<TResponse, TBody = unknown>({
    url,
    method = "GET",
    body,
    headers = {},
  }: RequestOptions<TBody>): Promise<ApiResponse<TResponse>> {
    try {
      const res = await fetch(`${this.baseUrl}${url}`, {
        method,
        headers: { ...this.defaultHeaders, ...headers },
        body: body ? JSON.stringify(body) : undefined,
      });

      if (!res.ok) {
        const errorData = await res.json().catch(() => null);
        return {
          data: null,
          error: errorData?.message ?? `Error ${res.status}: ${res.statusText}`,
          ok: false,
        };
      }

      const data: TResponse = await res.json();
      return { data, error: null, ok: true };

    } catch (err) {
      return {
        data: null,
        error: err instanceof Error ? err.message : "Error inesperado.",
        ok: false,
      };
    }
  }

  // Métodos de conveniencia
  get<TResponse>(url: string, headers?: Record<string, string>) {
    return this.request<TResponse>({ url, method: "GET", headers });
  }

  post<TResponse, TBody = unknown>(url: string, body: TBody, headers?: Record<string, string>) {
    return this.request<TResponse, TBody>({ url, method: "POST", body, headers });
  }

  put<TResponse, TBody = unknown>(url: string, body: TBody, headers?: Record<string, string>) {
    return this.request<TResponse, TBody>({ url, method: "PUT", body, headers });
  }

  patch<TResponse, TBody = unknown>(url: string, body: TBody, headers?: Record<string, string>) {
    return this.request<TResponse, TBody>({ url, method: "PATCH", body, headers });
  }

  delete<TResponse>(url: string, headers?: Record<string, string>) {
    return this.request<TResponse>({ url, method: "DELETE", headers });
  }

  // Setear token dinámicamente (útil post-login)
  setAuthToken(token: string) {
    this.defaultHeaders["Authorization"] = `Bearer ${token}`;
  }

  clearAuthToken() {
    delete this.defaultHeaders["Authorization"];
  }
}

// Instancia global lista para usar
export const apiClient = new ApiClient("/api/v1");