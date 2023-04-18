import React from 'react'
import { SectionIdEnum } from '../../types';

export type SectionContainerProps = {
    children: React.ReactNode;
    sectionId: SectionIdEnum;
};


export const SectionContainer: React.FC<SectionContainerProps> = ({ children, sectionId }) => {
    return (
        <div id={sectionId} key={sectionId}>
            <main>
                <div>
                    {children}
                </div>
            </main>
        </div>
    );
};
