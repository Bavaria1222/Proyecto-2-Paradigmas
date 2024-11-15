import { lazy } from 'react';
import type { RouteObject } from 'react-router';
import { Navigate, Outlet } from 'react-router-dom';
import { Layout } from 'src/layouts';

import Prioridad from 'src/pages/prioridad/prioridad';

import PrioridadLista from 'src/pages/prioridad/prioridads';
import TareaLista from 'src/pages/tarea/tareas';
// import { Layout as LayoutBase } from "src/layouts/base";
import { routes } from './routes';




function ProtectedRoute({ children }) {
  const userData = JSON.parse(localStorage.getItem('userData'));
  return userData ? (
    children
  ) : (
    <Navigate
      to="/login"
      replace
    />
  );
}

const HomePage = lazy(() => import('src/pages/index'));
const PageExample = lazy(() => import('src/pages/page-example'));
const Error404Page = lazy(() => import('src/pages/404'));
const normalizeRoute = (route: string) => {
  return route.slice(1);
};
export const routesOutlets: RouteObject[] = [
  {
    path: '/',
    element: (
      <Layout>
        <Outlet />
      </Layout>
    ),
    children: [
      { index: true, element: <HomePage /> },
      { path: 'page-example', element: <PageExample /> },
      { path: 'prioridads', element: <PrioridadLista /> },
      { path: 'prioridad', element: <Prioridad /> },
      { path: 'prioridad/:id', element: <Prioridad /> },
      { path: 'tarea', element: <Prioridad /> },
      { path: 'tareas', element: <TareaLista /> },


    ],
  },
  // Ruta de registro sin Layout
  { path: '*', element: <Error404Page /> },
];
