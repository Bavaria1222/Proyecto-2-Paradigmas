import ChatBubbleOutlineRoundedIcon from '@mui/icons-material/ChatBubbleOutlineRounded';
import MenuRoundedIcon from '@mui/icons-material/MenuRounded';
import NotificationsNoneRoundedIcon from '@mui/icons-material/NotificationsNoneRounded';
import SearchRoundedIcon from '@mui/icons-material/SearchRounded';
import WidgetsOutlinedIcon from '@mui/icons-material/WidgetsOutlined';
import {
  alpha,
  AppBar,
  Avatar,
  Box,
  Button,
  Divider,
  IconButton,
  Stack,
  styled,
  Theme,
  useMediaQuery,
  useTheme,
} from '@mui/material';
import PropTypes from 'prop-types';
import { FC } from 'react';
import { Logo } from 'src/components/base/logo';
import { PulseBadge } from 'src/components/base/styles/pulse-badge';
import { useSidebarContext } from 'src/contexts/sidebar-context';
import { useDialog } from 'src/hooks/use-dialog';
import { usePopover } from 'src/hooks/use-popover';
import useScrollDirection from 'src/hooks/use-scroll-direction';
import { HEADER_HEIGHT, SIDEBAR_WIDTH, SIDEBAR_WIDTH_COLLAPSED } from 'src/theme/utils';
import { useNavigate } from 'react-router-dom';

const HeaderWrapper = styled(AppBar)(({ theme }) => ({
  height: HEADER_HEIGHT,
  color: 'inherit',
  background: alpha(theme.palette.background.paper, 0.9),
  backdropFilter: 'blur(8px)',
  boxShadow: theme.shadows[9],
  right: 0,
  left: 'auto',
  display: 'flex',
  transition: theme.transitions.create(['height']),
}));

interface HeaderProps {
  onMobileNav?: () => void;
}

export const Header: FC<HeaderProps> = (props) => {
  const { onMobileNav } = props;
  const scroll = useScrollDirection();
  const lgUp = useMediaQuery((theme: Theme) => theme.breakpoints.up('lg'));
  const smUp = useMediaQuery((theme: Theme) => theme.breakpoints.up('sm'));
  const { isSidebarCollapsed } = useSidebarContext();
  const dialog = useDialog();
  const popover = usePopover<HTMLButtonElement>();
  const theme = useTheme();
  const navigate = useNavigate();

  const notifications = useDialog();
  const widgets = useDialog();
  const popoverChat = usePopover<HTMLButtonElement>();
  // Extraer datos del usuario desde localStorage
  const userData = JSON.parse(localStorage.getItem('userData') || '{}');
  const fullName = `${userData.nombre} ${userData.apellido1}`;



  return (
    <HeaderWrapper
      role="banner"
      sx={{
        height: scroll === 'down' ? HEADER_HEIGHT : HEADER_HEIGHT * 1.5,
        width: {
          xs: '100%',
          lg: `calc(100% - ${isSidebarCollapsed ? SIDEBAR_WIDTH_COLLAPSED : SIDEBAR_WIDTH}px)`,
        },
      }}
    >
      <Stack
        px={2}
        flex={1}
        direction="row"
        justifyContent="space-between"
        alignItems="center"
      >
        <Stack
          direction="row"
          divider={
            <Divider
              orientation="vertical"
              flexItem
            />
          }
          alignItems="center"
          spacing={{ xs: 1, sm: 2 }}
        >
          {!lgUp && <Logo isLinkStatic />}

        </Stack>
        <Stack
          direction="row"
          divider={
            <Divider
              orientation="vertical"
              flexItem
            />
          }
          alignItems="center"
          spacing={{ xs: 1, sm: 2 }}
        >
          <Stack
            display="flex"
            spacing={0.5}
            direction="row"
            alignItems="center"
          >

          </Stack>


        </Stack>
      </Stack>
    </HeaderWrapper>
  );
};

Header.propTypes = {
  onMobileNav: PropTypes.func,
};
