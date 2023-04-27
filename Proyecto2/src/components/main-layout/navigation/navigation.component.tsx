import React, { useState } from 'react';
import { Close, Menu } from '@mui/icons-material';
import { AppBar, Box, Button, Dialog, Hidden, IconButton, Slide, Toolbar, Typography, } from '@mui/material';
import { TransitionProps } from '@mui/material/transitions';
import { SectionIdEnum } from '../../../types';
import AnchorLink from 'react-anchor-link-smooth-scroll';

export type NavigationProps = {
    isSmall: boolean;
};

const navigationItems = [
    {
        text: "Intro",
        to: SectionIdEnum.intro,
    },
    {
        text: "Errores",
        to: SectionIdEnum.intro,//errores
    },
    {
        text: "AST",
        to: SectionIdEnum.intro,//ast
    },
    {
        text: "Tabla Simbolos",
        to: SectionIdEnum.intro,//tabla
    },
];

const Transition = React.forwardRef((
    props: TransitionProps & {
        children: React.ReactElement<any, any>;
    },
    ref: React.Ref<unknown>,
) => {
    return <Slide direction="left" ref={ref} {...props} />;
});

export const Navigation: React.FC<NavigationProps> = ({ isSmall }) => {
    const [ open, setOpen ] = useState(false);
    const onOpenHandler = () => setOpen(true);
    const onCloseHandler = () => setOpen(false);
    const mappedItems = (
        navigationItems.map(({to, text}) => {
            return (
                <AnchorLink key={to} href={`#${to}`} offset={isSmall ? '80px' : '89px'} className="all_unset">
                    <Button color="inherit" size="large" fullWidth={isSmall} onClick={onCloseHandler}>{text}</Button>
                </AnchorLink>
            );
        })
    );
    return (
        <>
        <Hidden smDown>
            <Box display="flex" gap={2}>{mappedItems}</Box>
        </Hidden>
        <Hidden smUp>
            <IconButton color='inherit' onClick={onOpenHandler}>
                <Menu />
            </IconButton>
            <Dialog open={open} fullScreen fullWidth TransitionComponent={Transition} hideBackdrop>
                <AppBar position='relative' sx={{background: 'white', color: 'text.primary'}}>
                    <Toolbar>
                        <Typography variant='h5' sx={{flexGrow: 1}}>Menu</Typography>
                        <IconButton color='inherit' onClick={onCloseHandler}>
                            <Close />
                        </IconButton>
                    </Toolbar>
                </AppBar>
                <Box display='flex' flexDirection='column' py={3} width='100%'>
                    {mappedItems}
                </Box>
            </Dialog>
        </Hidden>
        </>
    );
};