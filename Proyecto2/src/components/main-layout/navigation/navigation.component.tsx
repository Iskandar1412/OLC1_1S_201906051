import React from 'react'
import { SectionIdEnum } from '../../../types';

export type NavigationProps = {
    isSmall: boolean;
};

const navigationItems = [
    {
        text: "About",
        to: SectionIdEnum.about,
    },
    {
        text: "Skills",
        to: SectionIdEnum.skills,
    },
];