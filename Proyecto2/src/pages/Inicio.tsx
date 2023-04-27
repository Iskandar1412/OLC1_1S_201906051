import React, { Fragment } from 'react'

import { SectionIdEnum } from '../types';
import { IntroSection } from '../sections';/*
import { ErroresSection } from '../sections';
import { TablaSection } from '../sections';
import { ASTSection } from '../sections';*/
import { SectionContainer, MainLayout } from '../components';


const sections = [
  {
    sectionId: SectionIdEnum.intro,
    component: <IntroSection />
  },/*
  {
    sectionId: SectionIdEnum.errores,
    component: <ErroresSection />,
  },
  {
    sectionId: SectionIdEnum.ast,
    component: <ASTSection />,
  },
  {
    sectionId: SectionIdEnum.tabla,
    component: <TablaSection />,
  },*/
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