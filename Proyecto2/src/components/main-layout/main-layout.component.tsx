import React from 'react'
import { Toolbar, Box, Typography, useMediaQuery, useTheme, } from '@mui/material';
import AnchorLink from 'react-anchor-link-smooth-scroll';
import { SectionIdEnum } from '../../types';
import { Navigation } from './navigation/navigation.component';
import Logo from '../../logos.png'

export type MainLayoutProps = {
    children: React.ReactNode;
};

export const MainLayout: React.FC<MainLayoutProps> =  ({children}) => {
    const theme = useTheme();
    const isSmall = useMediaQuery(theme.breakpoints.down('sm'));
    return (
        <Box>
            <nav className='nav2'>
                <Toolbar>
                    <Box flexGrow={1} className='dispinic'>
                        <AnchorLink href={`#${SectionIdEnum.intro}`} offset={isSmall ? '80px' : '89px'} className='all_unset'>
                            <Box display='flex' alignItems='center' gap={0.5} sx={{cursor: 'pointer'}}>
                                <img width='50px' height='50px' src={Logo} alt="logo" className='imagembr' />
                                <Typography variant='h5' sx={{ width: 'fit-content'}}>Iskandar</Typography>
                            </Box>
                        </AnchorLink>
                    </Box>
                    <Box className='dsp'>
                        <Navigation isSmall={isSmall} />
                    </Box>
                </Toolbar>
            </nav>
            <Box>
                <div className='margen' />
                {children}
            </Box>
        </Box>
    );
};