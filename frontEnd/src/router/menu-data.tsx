import ArchiveIcon from '@heroicons/react/24/outline/ArchiveBoxIcon';
import BoltIcon from '@heroicons/react/24/outline/BoltIcon';
import BookOpenIcon from '@heroicons/react/24/outline/BookOpenIcon';
import ClipboardDocumentCheckIcon from '@heroicons/react/24/outline/ClipboardDocumentCheckIcon';
import ClipboardDocumentIcon from '@heroicons/react/24/outline/ClipboardDocumentIcon';
import CogIcon from '@heroicons/react/24/outline/CogIcon';
import FlagIcon from '@heroicons/react/24/outline/FlagIcon';
import HomeIcon from '@heroicons/react/24/outline/HomeIcon';
import { Box } from '@mui/material';
import { useMemo } from 'react';
import { useTranslation } from 'react-i18next';
import { MenuItem } from 'src/router/menuItem';
import { routes } from 'src/router/routes';

export const useMenuItems = (): MenuItem[] => {
  const { t } = useTranslation();
  return useMemo(() => {
    return [
      {
        title: '',
        subMenu: [
          {
            title: 'Inicio',
            icon: (
              <Box
                width={24}
                height={24}
              >
                <HomeIcon />
              </Box>
            ),
            route: routes.index,
          },
          {
            title: 'Tarea',
            icon: (
              <Box
                width={24}
                height={24}
              >
                <ClipboardDocumentIcon />
              </Box>
            ),
            route: routes.tareas,
          },
          {
            title: 'Clima',
            icon: (
              <Box
                width={24}
                height={24}
              >
                <BoltIcon />
              </Box>
            ),
            route: routes.climas,
          },
          {
            title: 'Actividad',
            icon: (
              <Box
                width={24}
                height={24}
              >
                <BookOpenIcon />
              </Box>
            ),
            route: routes.dummy,
          },
          {
            title: 'Prioridad',
            icon: (
              <Box
                width={24}
                height={24}
              >
                <FlagIcon />
              </Box>
            ),
            route: routes.prioridads,
          },
          {
            title: 'Plan Optimizado',
            icon: (
              <Box
                width={24}
                height={24}
              >
                <CogIcon />
              </Box>
            ),
            route: routes.dummy,
          },
        ],
      },
    ];
  }, [t]);
};
