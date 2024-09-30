export type PageAttributesSection = {
  name: string;
  version?: number;
  kuzzleMajor: number;
  section: string;
  subsection?: string;
  icon?: string;
  released: boolean;
  deprecated?: boolean;
  closedSources?: boolean;
  deprecatedBannerComponent?: string;
};

export type PageAttributesSectionWithPath = PageAttributesSection & { path: string };

export type PageAttributesPluginOptions = {
  sections: Record<string, PageAttributesSection>;
};

export type ExtraPageData = {
  fullPath: string;
  sectionsByPath: Record<string, PageAttributesSection>;
  sectionList: PageAttributesSectionWithPath[];
  currentSection: PageAttributesSectionWithPath;
};
