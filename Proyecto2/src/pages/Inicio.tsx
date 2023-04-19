import React, { Fragment, useState } from 'react'

import { SectionIdEnum } from '../types';
import { IntroSection } from '../sections';
import { SectionContainer, MainLayout } from '../components';


const sections = [
  {
    sectionId: SectionIdEnum.intro,
    component: <IntroSection />
  },
  {
    sectionId: SectionIdEnum.errores,
    component: <IntroSection />,
  },
  {
    sectionId: SectionIdEnum.ast,
    component: <IntroSection />,
  },
  {
    sectionId: SectionIdEnum.tabla,
    component: <IntroSection />,
  },
];

const Inicio: React.FC = () => {
  return (
    <>
    <div id='layoutSidenav_content'>
        <main>
          <MainLayout>
          
            {sections.map(({component, sectionId}) => {
              return <SectionContainer sectionId={sectionId} key={sectionId}>
                {component}
              </SectionContainer>;
            })}
          </MainLayout>
        </main>
    </div>
    </>
  )
};

export default Inicio;