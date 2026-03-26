export interface User {
  id: string;
  username: string;
  email: string;
  creationDate: string;
  active: boolean;
  balance: number;
  rolesList: string[];
}