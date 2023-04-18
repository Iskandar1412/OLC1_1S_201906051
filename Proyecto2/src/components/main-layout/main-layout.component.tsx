import React from 'react'

export type MainLayoutProps = {
    children: React.ReactNode;
};

export const MainLayout: React.FC<MainLayoutProps> =  ({children}) => {
    return (
        <h1>{children}adf</h1>
    );
};