import { alpha, Box, Link, useTheme } from '@mui/material';
import { RouterLink } from './router-link';

const logoImage = '/lg.png';

interface LogoProps {
  dark?: boolean;
  isLinkStatic?: boolean;
}

export const Logo = ({ dark = false, isLinkStatic = false }: LogoProps) => {
  const theme = useTheme();

  const color = dark
    ? theme.palette.common.white
    : theme.palette.mode === 'dark'
      ? theme.palette.common.white
      : theme.palette.common.black;

  const linkProps = isLinkStatic
    ? {
      href: '',
      onClick: (e: { preventDefault: () => any }) => e.preventDefault(),
    }
    : { href: '/' };

  return (
    <Box
      sx={{
        position: 'relative',
        transition: (theme) => theme.transitions.create(['transform']),
        transform: 'scale(1)',
        '&:hover': {
          transform: 'scale(1.05)',
        },
        display: 'flex',
        alignItems: 'center',
      }}
    >
      <Link
        component={RouterLink}
        {...linkProps}
        sx={{
          color: color,
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          '&:hover .MuiBadge-badge': {
            opacity: 1,
            visibility: 'initial',
          },
        }}
      >
      </Link>
    </Box>
  );
};
